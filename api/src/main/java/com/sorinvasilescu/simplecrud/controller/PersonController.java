package com.sorinvasilescu.simplecrud.controller;

import com.sorinvasilescu.simplecrud.model.Person;
import com.sorinvasilescu.simplecrud.repository.PersonRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @Operation(summary = "Get list of persons", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("persons")
    public ResponseEntity<Page<Person>> getPersonList(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(personRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.Direction.ASC, "id")));
    }

    @Operation(summary = "Get person by id", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
        Optional<Person> result = personRepository.findById(id);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result.get());
    }

    @Operation(summary = "Add new person", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person result = personRepository.save(person);
        return ResponseEntity.ok(personRepository.save(person));
    }

    @Operation(summary = "Edit existing person", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("person")
    @Transactional
    public ResponseEntity<Person> editPerson(@RequestBody Person person) {
        Optional<Person> result = personRepository.findById(person.getId());
        if (result.isEmpty()) return addPerson(person);
        Person toSave = result.get().replaceFields(person);
        return ResponseEntity.ok(personRepository.save(toSave));
    }

    @Operation(summary = "Delete person by id", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("person/{id}")
    @Transactional
    public ResponseEntity<Person> deletePerson(@PathVariable Integer id) {
        Optional<Person> result = personRepository.findById(id);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        personRepository.deleteById(id);
        return ResponseEntity.ok(result.get());
    }
}
