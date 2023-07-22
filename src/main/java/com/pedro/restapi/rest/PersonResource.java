package com.pedro.restapi.rest;

import com.pedro.restapi.domain.Person;
import com.pedro.restapi.repository.PersonRepository;
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

    @GetMapping
    public List<Person> getPersons () {
        return personRepository.findAll();
    }



    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody PersonDTO personDTO) throws URISyntaxException {
        log.info("Rest request to save Person: {}", personDTO);
        if (personDTO.getId() != null) {
            throw new PersonErrorException("A new movement cannot already have an ID");
        }
        Person person = personService.save(personDTO);
        return ResponseEntity.ok().build();
    }

//    @PutMapping("/{id}")

    @DeleteMapping("{/id}")
    public ResponseEntity<Void> deletePerson (@PathVariable Long id) {
        try {
            log.info("Deleting person...");
            personService.delete(id);
        } catch (Exception e) {
            throw new PersonErrorException("Cannot delete the person");
        }
        log.info("Person deleted!");
        return ResponseEntity.ok().build();
    }



}
