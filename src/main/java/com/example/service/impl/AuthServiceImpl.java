package com.example.service.impl;

import com.example.exceptions.InvalidUsernameOrPasswordException;
import com.example.exceptions.PasswordsDoNotMatchException;
import com.example.exceptions.UsernameAlreadyExistsException;
import com.example.model.*;
import com.example.model.Enumerations.Role;
import com.example.repository.UserRepository;
import com.example.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void validateUsernameAndPassword(String username, String password, String rpassword) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }
        if (!password.equals(rpassword)) {
            throw new PasswordsDoNotMatchException();
        }
    }

    private void checkIfUsernameExists(String username) {
        if (userRepository.findByUsername(username) != null) {
            throw new UsernameAlreadyExistsException(username);
        }
    }

    private void registerUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void registerBand(String name, String username, String number, String password,
                             String rpassword, String role, Integer price) {
        validateUsernameAndPassword(username, password, rpassword);
        checkIfUsernameExists(username);
        Band band = new Band(LocalDate.now(), name, username, passwordEncoder.encode(password), number, Role.ROLE_BAND, price);
        registerUser(band);
    }

    @Override
    public void registerCatering(String name, String username, String number, String password,
                                 String rpassword, String role, Integer price, String address) {
        validateUsernameAndPassword(username, password, rpassword);
        checkIfUsernameExists(username);
        Catering catering = new Catering(LocalDate.now(), name, username, passwordEncoder.encode(password), number, Role.ROLE_CATERING, price, address);
        registerUser(catering);
    }

    @Override
    public void registerClient(String name, String username, String number, String password,
                               String rpassword, String role) {
        validateUsernameAndPassword(username, password, rpassword);
        checkIfUsernameExists(username);
        Client client = new Client(LocalDate.now(), name, username, passwordEncoder.encode(password), number, Role.ROLE_CLIENT, 0);
        registerUser(client);
    }

    @Override
    public void registerPhotographer(String name, String username, String number, String password,
                                     String rpassword, String role, Integer price, String portfolio) {
        validateUsernameAndPassword(username, password, rpassword);
        checkIfUsernameExists(username);
        Photographer photographer = new Photographer(LocalDate.now(), name, username, passwordEncoder.encode(password), number, Role.ROLE_PHOTOGRAPHER, price, portfolio);
        registerUser(photographer);
    }

}