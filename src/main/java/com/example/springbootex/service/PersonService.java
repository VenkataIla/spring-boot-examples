package com.example.springbootex.service;

import com.example.springbootex.exception.ResourceNotFoundException;
import com.example.springbootex.modal.Person;
import com.example.springbootex.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    PersonRepo personRepo;

    public List< Person > getAllPersons() {
        return personRepo.findAll();
    }
    public Optional<Person> getPersonById(Long employeeId)
            throws ResourceNotFoundException {
        return personRepo.findById(employeeId);
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
        final Person updatedPerson = personRepo.save(employee);
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
