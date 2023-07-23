package com.pedro.restapi.rest;

import com.pedro.restapi.domain.Person;
import com.pedro.restapi.repository.PersonRepository;
import com.pedro.restapi.rest.errors.DepartmentErrorException;
import com.pedro.restapi.rest.errors.PersonErrorException;
import com.pedro.restapi.service.PersonService;
import com.pedro.restapi.service.dto.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PersonResource {

    private PersonService personService;
    private PersonRepository personRepository;
    private final Logger log = LoggerFactory.getLogger(PersonResource.class);


    public PersonResource(PersonService personService, PersonRepository personRepository) {
        this.personService = personService;
        this.personRepository = personRepository;
    }

//    @GetMapping
//    public List<Person> getPersons () {
//        return personRepository.findAll();
//    }


    @GetMapping("")
    public ResponseEntity<List<PersonDTO>> getPeopleWithTotalTime() {
        log.info("Rest request to get people");
        List<PersonDTO> peopleList = personService.getPeopleList();
        return ResponseEntity.ok(peopleList);
    }

//    @GetMapping("/gastos")
//    public ResponseEntity<List<PersonDTO>> getPersonWithTaskDuration() {
//        List<PersonDTO> people = personService.getNameDepartmentAvgTask(); // pega as pessoas com a duração média de
//        // tarefas
//
//        return ResponseEntity.ok(people);
//    }


    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) throws URISyntaxException {
        log.info("Rest request to save Person: {}", person);
        if (person.getId() != null) {
            throw new PersonErrorException("A new person cannot already have an ID");
        }
        return ResponseEntity.ok(personService.save(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id,@RequestBody Person person) throws URISyntaxException, DepartmentErrorException {
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
        log.info("Person deleted!");
        return ResponseEntity.ok().build();
    }






}
