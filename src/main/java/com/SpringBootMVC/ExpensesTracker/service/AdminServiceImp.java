package com.SpringBootMVC.ExpensesTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SpringBootMVC.ExpensesTracker.entity.Admin;
import com.SpringBootMVC.ExpensesTracker.repository.AdminRepository;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public boolean authenticateAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        return admin != null && admin.getPassword().equals(password);
    }

    @Override
    public boolean registerAdmin(String username, String password) {
        if (adminRepository.findByUsername(username) != null) {
            return false; 
        }
        Admin newAdmin = new Admin(username, password);
        adminRepository.save(newAdmin);
        return true;
    }
}
