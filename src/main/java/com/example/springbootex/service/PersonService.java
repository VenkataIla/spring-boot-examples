package com.example.springbootex.service;

import com.example.springbootex.exception.ConnectionException;
import com.example.springbootex.exception.ResourceNotFoundException;
import com.example.springbootex.modal.Person;
import com.example.springbootex.repo.PersonRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class PersonService {
    @Autowired
    PersonRepo personRepo;

    public List< Person > getAllPersons() {
        return personRepo.findAll();
    }
    public Person getPersonById(Long employeeId)
            throws ResourceNotFoundException {
        Optional<Person> person = personRepo.findById(employeeId);
        if(person.isPresent())
        {
            return person.get();
        }
        return person.get();
    }

    public Person createPerson(Person employee) {
        return personRepo.save(employee);
    }

    public Person updatePerson(Long employeeId,
                                   Person employeeDetails) throws ResourceNotFoundException {
        Person employee = personRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        Person updatedPerson = null;
        try {
            updatedPerson = personRepo.save(employee);
        }catch (Exception e){
            throw new ConnectionException(e.getMessage());
        }
        return updatedPerson;
    }

    public Map < String, Boolean > deletePerson(Long employeeId)
            throws ResourceNotFoundException {
        Person employee = personRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + employeeId));

        personRepo.delete(employee);
        Map< String, Boolean > response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
