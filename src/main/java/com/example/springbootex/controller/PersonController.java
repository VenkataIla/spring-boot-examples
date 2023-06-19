package com.example.springbootex.controller;

import com.example.springbootex.exception.ResourceNotFoundException;
import com.example.springbootex.modal.Person;
import com.example.springbootex.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity< Person > getPersonById(@PathVariable(value = "id") Long personId)
            throws ResourceNotFoundException {
        Person person = personService.getPersonById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
        return ResponseEntity.ok().body(person);
    }

    @PostMapping("/persons")
    public Person createPerson(@Valid @RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity < Person > updatePerson(@PathVariable(value = "id") Long personId,
                                                      @Valid @RequestBody Person personDetails) throws ResourceNotFoundException {
        Person updatedPerson = personService.updatePerson(personId, personDetails);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/persons/{id}")
    public Map< String, Boolean > deletePerson(@PathVariable(value = "id") Long personId)
            throws ResourceNotFoundException {
        return personService.deletePerson(personId);
    }
}