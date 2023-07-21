package com.pedro.restapi.rest;

import com.pedro.restapi.domain.Person;
import com.pedro.restapi.repository.PersonRepository;
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
    public ResponseEntity<PersonDTO> createPersonDTO (@RequestBody PersonDTO personDTO) throws URISyntaxException {
        log.info("Rest request to save Person: {}", personDTO);
        if (personDTO.getId() != null) {
            throw new BadRequestAlertException("A new movement cannot already have an ID", ENTITY_NAME, "idexists");
        }
    }



}
