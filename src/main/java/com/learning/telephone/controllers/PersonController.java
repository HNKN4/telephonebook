package com.learning.telephone.controllers;

import com.learning.telephone.entities.Person;
import com.learning.telephone.services.PersonService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/save")
    protected ResponseEntity<?> savePerson(@RequestBody Person person) {
        return ResponseEntity.ok(personService.savePerson(person));
    }

    @GetMapping("/getAllPersons")
    protected ResponseEntity<?> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping("/getPerson")
    protected ResponseEntity<?> getPersonById (@Param("personId") Long personId) {
        return ResponseEntity.ok(personService.getPersonById(personId));
    }

    @DeleteMapping("/deletePerson")
    protected void deletePersonById (@Param("personId") Long personId) {
        personService.deletePersonById(personId);
    }

    @GetMapping("/getPersonByContact")
    protected ResponseEntity<?> getPersonByContact(String contact) {
        return ResponseEntity.ok(personService.getPersonByContact(contact));
    }

    @GetMapping("/getDuplicatesByContact")
    protected ResponseEntity<?> getDuplicatesByContact() {
        return ResponseEntity.ok(personService.getDuplicatesByContact());
    }

    @GetMapping("/getDuplicatesByNameAndBirthday")
    protected ResponseEntity<?> getDuplicatesByNameAndBirthday() {
        return ResponseEntity.ok(personService.getDuplicatesByNameAndBirthday());
    }

    @GetMapping("/reportGeneration")
    protected ResponseEntity<?> getReport() throws IOException {
        byte[] resource = personService.generateReport();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + "PersonReport.csv" + "\"")
                .body(resource);
    }
}