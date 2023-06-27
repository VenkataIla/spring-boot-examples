package com.example.springbootex.repo;

import com.example.springbootex.enitity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
