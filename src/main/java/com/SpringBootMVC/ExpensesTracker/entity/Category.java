package com.SpringBootMVC.ExpensesTracker.entity;

import com.SpringBootMVC.ExpensesTracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
    @Id
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    List<Expense> expence;
    
    
    public Category() {
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
