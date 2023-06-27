package com.example.springbootex.controller;

import com.example.springbootex.exception.ResourceNotFoundException;
import com.example.springbootex.modal.Person;
import com.example.springbootex.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     *
     * @return
     */
    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        log.info("start of the controller");
        return personService.getAllPersons();
    }

    /**
     *
     * @param personId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/persons/{id}")
    public ResponseEntity< Person > getPersonById(@PathVariable(value = "id") Long personId)
            throws ResourceNotFoundException {
        Person person = personService.getPersonById(personId);
        return ResponseEntity.ok().body(person);
    }

    /**
     *
     * @param person
     * @return
     */
    @PostMapping("/persons")
    public Person createPerson(@Valid @RequestBody Person person) {
        return personService.createPerson(person);
    }

    /**
     *
     * @param personId
     * @param personDetails
     * @return
     * @throws ResourceNotFoundException
     */

    @PutMapping("/persons/{id}")
    public ResponseEntity < Person > updatePerson(@PathVariable(value = "id") Long personId,
                                                      @Valid @RequestBody Person personDetails) throws ResourceNotFoundException {
        Person updatedPerson = personService.updatePerson(personId, personDetails);
        return ResponseEntity.ok(updatedPerson);
    }

    /**
     *
     * @param personId
     * @return
     * @throws ResourceNotFoundException
     */

    @DeleteMapping("/persons/{id}")
    public Map< String, Boolean > deletePerson(@PathVariable(value = "id") Long personId)
            throws ResourceNotFoundException {
        return personService.deletePerson(personId);
    }
}