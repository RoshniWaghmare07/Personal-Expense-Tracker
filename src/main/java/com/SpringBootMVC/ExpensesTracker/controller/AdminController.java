package com.SpringBootMVC.ExpensesTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SpringBootMVC.ExpensesTracker.entity.Admin;
import com.SpringBootMVC.ExpensesTracker.service.AdminService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "adminlogin";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
        if (adminService.authenticateAdmin(username, password)) {
            session.setAttribute("admin", username);
            return "redirect:/admin/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid credentials.");
            return "redirect:adminlogin";
        }
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "AdminRegister"; 
    }

    @PostMapping("/register")
    public String registerAdmin(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        if (adminService.registerAdmin(username, password)) {
            redirectAttributes.addFlashAttribute("successMessage", "Admin registered successfully. Please log in.");
            return "redirect:adminlogin";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Admin already exists.");
            return "redirect:AdminRegister";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
