package com.example.service.impl;

import com.example.exceptions.NoSuchIDException;
import com.example.model.Enumerations.Role;
import com.example.model.Event;
import com.example.model.User;
import com.example.repository.ClientRepository;
import com.example.repository.EventRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final ClientRepository clientRepository;
    private final EventRepository eventRepository;

    public UserServiceImpl(UserRepository userRepository, ClientRepository clientRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {

        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).stream().findFirst()
                .orElseThrow(() -> new NoSuchIDException(id));
    }

    @Override
    public List<User> getUsersByRole(String roleFilter) {
        Role role = Role.valueOf(roleFilter);
        return this.userRepository.findAllByRole(role);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public List<Event> getEventsByUser(User user) {
        return this.eventRepository.findByClientId(user.getId());
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Override
    public List<User> findAllActiveUsers() {
        List<User> activeUsers = new ArrayList<>();
        for (User u : userRepository.findAll()) {
            if (u.getIsDeleted() == false) {
                activeUsers.add(u);
            }
        }
        return activeUsers;
    }

    @Override
    public List<User> getActiveUsersByRole(String roleFilter) {
        return this.findAllActiveUsers().stream()
                .filter(user -> user.getRole().toString().equalsIgnoreCase(roleFilter))
                .collect(Collectors.toList());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user.getIsDeleted()) {
            throw new UsernameNotFoundException("No such username");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(String.valueOf(user.getRole()))
                .build();
    }
}

