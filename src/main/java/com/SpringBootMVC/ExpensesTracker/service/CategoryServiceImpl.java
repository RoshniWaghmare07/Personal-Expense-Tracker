package com.SpringBootMVC.ExpensesTracker.service;



import com.SpringBootMVC.ExpensesTracker.entity.Category;
import com.SpringBootMVC.ExpensesTracker.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category addCategory(String name) {
        if (categoryRepository.findByName(name) != null) {
            throw new IllegalArgumentException("Category already exists!");
        }
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(int id, String newName) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(newName);
            return categoryRepository.save(category);
        } else {
            throw new IllegalArgumentException("Category not found!");
        }
    }

    @Override
    public void deleteCategory(int id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category does not exist!");
        }
        categoryRepository.deleteById(id);
    }
}
