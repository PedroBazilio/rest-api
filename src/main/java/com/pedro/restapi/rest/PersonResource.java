package com.pedro.restapi.rest;

import com.pedro.restapi.domain.Person;
import com.pedro.restapi.rest.errors.DepartmentErrorException;
import com.pedro.restapi.rest.errors.PersonErrorException;
import com.pedro.restapi.service.PersonService;
import com.pedro.restapi.service.dto.PersonAvgTimeTaskDTO;
import com.pedro.restapi.service.dto.PersonTotalTimeTaskDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PersonResource {

    private PersonService personService;
    private final Logger log = LoggerFactory.getLogger(PersonResource.class);


    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    public ResponseEntity<List<PersonTotalTimeTaskDTO>> getPeopleWithTotalTime() {
        log.info("Rest request to get people");
        List<PersonTotalTimeTaskDTO> peopleList = personService.getPeopleList();
        return ResponseEntity.ok(peopleList);
    }

    @GetMapping("/gastos")
    public ResponseEntity<List<PersonAvgTimeTaskDTO>> getPeopleWithAvgTime() {
        log.info("Rest request to get people");
        List<PersonAvgTimeTaskDTO> peopleList = personService.getPeopleListAvgTime();
        return ResponseEntity.ok(peopleList);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        log.info("Rest request to save Person: {}", person.getName());
        return ResponseEntity.ok(personService.save(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) throws DepartmentErrorException {
        log.info("Rest request to update Person: {}", person);
        return ResponseEntity.ok(personService.update(id, person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson (@PathVariable Long id) {
        log.info("Rest request to delete Person: {}", id);
        try {
            personService.delete(id);
        } catch (Exception e) {
            throw new PersonErrorException("Cannot delete the person");
        }
        return ResponseEntity.ok().build();
    }

}
