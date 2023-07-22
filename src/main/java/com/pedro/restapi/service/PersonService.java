package com.pedro.restapi.service;

import com.pedro.restapi.domain.Person;
import com.pedro.restapi.repository.PersonRepository;
import com.pedro.restapi.rest.PersonResource;
import com.pedro.restapi.service.dto.PersonDTO;
import com.pedro.restapi.service.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    PersonRepository personRepository;

    PersonMapper personMapper;

    private final Logger log = LoggerFactory.getLogger(PersonService.class);


    public PersonService(PersonRepository personRepository) {
        super();
        this.personRepository = personRepository;
    }

    public Person save(PersonDTO personDTO) {
        log.info("Saving new person");
        Person person = personMapper.toEntity(personDTO);
        personRepository.save(person);
        log.info("Person {} saved with success", person.getName());
        return person;
    }

    public void delete(Long id) {
        log.info("Request to delete person");
        personRepository.deleteById(id);
    }


}
