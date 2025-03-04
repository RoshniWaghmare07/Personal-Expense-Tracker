package com.SpringBootMVC.ExpensesTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SpringBootMVC.ExpensesTracker.entity.Category;
import com.SpringBootMVC.ExpensesTracker.service.CategoryService;


@Controller
@RequestMapping("/admin/categories")
@PreAuthorize("hasRole('ADMIN')") 
public class AdminCategory {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
    	
    	
    	
        model.addAttribute("categories",categoryService.findAllCategories() );
        return "admin/categories"; 
    }

    @PostMapping("/add")
    public String addCategory(@RequestParam String name, RedirectAttributes redirectAttributes) {
        try {
            categoryService.addCategory(name);
            redirectAttributes.addFlashAttribute("successMessage", "Category added successfully.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id, RedirectAttributes redirectAttributes) {
        categoryService.deleteCategory(id);
        redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully.");
        return "redirect:/admin/categories";
    }

    
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable int id, @RequestParam String name, RedirectAttributes redirectAttributes) {
        try {
            categoryService.updateCategory(id, name);
            redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/categories";
    }
}
