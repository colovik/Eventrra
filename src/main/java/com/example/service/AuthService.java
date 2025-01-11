package com.example.service;

import com.example.model.Catering;
import com.example.model.User;

public interface AuthService{
    User login(String username, String password);

//    void registerUser(String name, String username, String number, String password,
//                      String rpassword,  Role role);

    void registerBand(String name, String username, String number, String password,
                      String rpassword, String role, Integer price);

    void registerCatering(String name, String username, String number, String password,
                          String rpassword,  String role, Integer price, String address);

    void registerClient(String name, String username, String number, String password,
                        String rpassword,  String role);

    void registerPhotographer(String name, String username, String number, String password,
                              String rpassword, String role, Integer price, String portfolio);

    void registerWaiter (String name, String username, String number, String password,
                         String rpassword, String role, Integer free_day,
                         Integer experience, Catering catering);
}
