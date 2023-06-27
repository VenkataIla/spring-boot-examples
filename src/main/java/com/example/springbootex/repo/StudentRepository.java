package com.example.springbootex.repo;

import com.example.springbootex.enitity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
