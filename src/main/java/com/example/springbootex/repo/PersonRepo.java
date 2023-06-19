package com.example.springbootex.repo;

import com.example.springbootex.modal.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface PersonRepo extends JpaRepository<Person, Long> {

}
