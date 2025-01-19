package com.example.service.impl;

import com.example.model.Admin;
import com.example.repository.AdminRepository;
import com.example.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void save(Admin admin) {
        this.adminRepository.save(admin);
    }

    @Override
    public void delete(Admin admin) {
        admin.setIsDeleted(true);
        adminRepository.save(admin);
    }

}
