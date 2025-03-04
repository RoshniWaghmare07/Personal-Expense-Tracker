package com.SpringBootMVC.ExpensesTracker.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "login-page"; // Ensure this is the correct Thymeleaf template name
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            redirectAttributes.addFlashAttribute("loginSuccess", "✅ You have successfully logged in!");
        }

        return "redirect:/showLoginPage";
    }
}
