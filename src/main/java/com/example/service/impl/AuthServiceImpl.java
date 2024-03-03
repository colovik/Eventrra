package com.example.service.impl;

import com.example.exceptions.InvalidUserCredentialsException;
import com.example.exceptions.InvalidUsernameOrPasswordException;
import com.example.exceptions.PasswordsDoNotMatchException;
import com.example.exceptions.UsernameAlreadyExistsException;
import com.example.model.*;
import com.example.model.Enumerations.Role;
import com.example.repository.UserRepository;
import com.example.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public void registerUser(String name, String username, String number, String password,
                             String rpassword, String role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(rpassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        userRepository.save(new User(name, username, number, password, Role.ROLE_USER));
    }

    @Override
    public void registerBand(String name, String username, String number, String password,
                             String rpassword, String role, Integer price) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(rpassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        userRepository.save(new Band(name, username, number, password, price, Role.ROLE_BAND));
    }

    @Override
    public void registerCatering(String name, String username, String number, String password,
                                 String rpassword, String role,
                                 Integer price, String address) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(rpassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        userRepository.save(new Catering(name, username, number, password, price, address, Role.ROLE_CATERING));
    }

    @Override
    public void registerClient(String name, String username, String number, String password,
                               String rpassword, String role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(rpassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        userRepository.save(new Client(name, username, number, password, Role.ROLE_CLIENT));
    }

    @Override
    public void registerPhotographer(String name, String username, String number, String password,
                                     String rpassword, String role, Integer price, String portfolio) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(rpassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        userRepository.save(new Photographer(name, username, number, password, price, portfolio, Role.ROLE_PHOTOGRAPHER));
    }

    @Override
    public void registerWaiter(String name, String username, String number, String password,
                               String rpassword, String role, Integer free_day,
                               Integer experience, Catering catering) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(rpassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        userRepository.save(new Waiter(name, username, number, password, free_day, experience, catering, Role.ROLE_WAITER));
    }

//    @Override
//    public void register(String name, String username, String number, String password,
//                      String rpassword, Role role) {
//        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
//            throw new InvalidUsernameOrPasswordException();
//        if (!password.equals(rpassword))
//            throw new PasswordsDoNotMatchException();
//        if(this.userRepository.findByUsername(username).isPresent())
//            throw new UsernameAlreadyExistsException(username);
//        userRepository.save(new User(name, username, number, password));
//    }
}