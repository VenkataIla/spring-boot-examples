package com.example.springbootex.controller;

import com.example.springbootex.exception.ResourceNotFoundException;
import com.example.springbootex.modal.Person;
import com.example.springbootex.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.ErrorResponse;
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
    @Operation(summary = "Gets Persons", description = "Persons must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid request object"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) }) })
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
    @Operation(summary = "Gets person by ID", description = "Person must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request object"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorResponse.class)) }) })
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
    @Operation(summary = "create person by person object", description = "Person must not null")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request object"),
            @ApiResponse(responseCode = "404", description = "Person api not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorResponse.class)) }) })
    @PostMapping("/person")
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
    @Operation(summary = "Gets person by ID", description = "Person must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request object"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorResponse.class)) }) })
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
    @Operation(summary = "Gets person by ID", description = "Person must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Person.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request object"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ErrorResponse.class)) }) })
    @DeleteMapping("/persons/{id}")
    public Map< String, Boolean > deletePerson(@PathVariable(value = "id") Long personId)
            throws ResourceNotFoundException {
        return personService.deletePerson(personId);
    }
}