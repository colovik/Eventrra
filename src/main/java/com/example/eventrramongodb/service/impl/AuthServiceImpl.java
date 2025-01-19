package com.example.eventrramongodb.service.impl;

import com.example.eventrramongodb.exceptions.*;
import com.example.eventrramongodb.model.*;
import com.example.eventrramongodb.model.Enumerations.Role;
import com.example.eventrramongodb.repository.*;
import com.example.eventrramongodb.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BandRepository bandRepository;
    private final ClientRepository clientRepository;
    private final PhotographerRepository photographerRepository;
    private final CateringRepository cateringRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, BandRepository bandRepository, ClientRepository clientRepository, PhotographerRepository photographerRepository, CateringRepository cateringRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bandRepository = bandRepository;
        this.clientRepository = clientRepository;
        this.photographerRepository = photographerRepository;
        this.cateringRepository = cateringRepository;
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
        this.bandRepository.save(band);
        registerUser(band);
    }

    @Override
    public void registerCatering(String name, String username, String number, String password,
                                 String rpassword, String role, Integer price, String address) {
        validateUsernameAndPassword(username, password, rpassword);
        checkIfUsernameExists(username);
        Catering catering = new Catering(LocalDate.now(), name, username,
                passwordEncoder.encode(password), number, Role.ROLE_CATERING, price, address);
        this.cateringRepository.save(catering);
        registerUser(catering);
    }

    @Override
    public void registerClient(String name, String username, String number, String password,
                               String rpassword, String role) {
        validateUsernameAndPassword(username, password, rpassword);
        checkIfUsernameExists(username);
        Client client = new Client(LocalDate.now(), name, username, passwordEncoder.encode(password), number, Role.ROLE_CLIENT, 0);
        this.clientRepository.save(client);
        registerUser(client);
    }

    @Override
    public void registerPhotographer(String name, String username, String number, String password,
                                     String rpassword, String role, Integer price, String portfolio) {
        validateUsernameAndPassword(username, password, rpassword);
        checkIfUsernameExists(username);
        Photographer photographer = new Photographer(LocalDate.now(), name, username, passwordEncoder.encode(password), number, Role.ROLE_PHOTOGRAPHER, price, portfolio);
        this.photographerRepository.save(photographer);
        registerUser(photographer);
    }

}