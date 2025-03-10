package com.SpringBootMVC.ExpensesTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SpringBootMVC.ExpensesTracker.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUsername(String username);
}
