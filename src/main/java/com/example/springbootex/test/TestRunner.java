package com.example.springbootex.test;

import com.example.springbootex.enitity.Book;
import com.example.springbootex.enitity.Student;
import com.example.springbootex.repo.BookRepository;
import com.example.springbootex.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {

        Book book = new Book(101,"DS");
        Book book1 = new Book(102,"Sb");
        bookRepository.save(book);
        bookRepository.save(book1);

        Student student = new Student(50,"SAM",book);
        Student student1 = new Student(60,"JHON",book);
        Student student2 = new Student(70,"ANG",book1);
        Student student3 = new Student(80,"BROC",book1);
       studentRepository.save(student);
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);



    }
}
