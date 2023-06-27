package com.example.springbootex.repository;

import com.example.springbootex.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Product,Integer> {
}