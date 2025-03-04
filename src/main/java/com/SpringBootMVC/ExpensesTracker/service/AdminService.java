package com.SpringBootMVC.ExpensesTracker.service;

public interface AdminService {
    boolean authenticateAdmin(String username, String password);
    boolean registerAdmin(String username, String password);  // Added registration method
}
