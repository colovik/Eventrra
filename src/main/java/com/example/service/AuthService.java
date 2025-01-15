package com.example.service;

public interface AuthService {

    void registerBand(String name, String username, String number, String password,
                      String rpassword, String role, Integer price);

    void registerCatering(String name, String username, String number, String password,
                          String rpassword, String role, Integer price, String address);

    void registerClient(String name, String username, String number, String password,
                        String rpassword, String role);

    void registerPhotographer(String name, String username, String number, String password,
                              String rpassword, String role, Integer price, String portfolio);

}
