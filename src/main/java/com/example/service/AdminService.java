package com.example.service;

import com.example.model.Admin;

public interface AdminService {

    void save(Admin admin);

    void delete(Admin admin);

    Admin findByUsername(String admin);
}
