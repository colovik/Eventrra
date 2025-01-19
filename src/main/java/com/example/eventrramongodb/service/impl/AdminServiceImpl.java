package com.example.eventrramongodb.service.impl;

import com.example.eventrramongodb.model.Admin;
import com.example.eventrramongodb.repository.AdminRepository;
import com.example.eventrramongodb.service.AdminService;
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
