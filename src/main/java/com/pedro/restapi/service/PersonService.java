package com.pedro.restapi.service;

import com.pedro.restapi.domain.Department;
import com.pedro.restapi.domain.Person;
import com.pedro.restapi.repository.DepartmentRepository;
import com.pedro.restapi.repository.PersonRepository;
import com.pedro.restapi.rest.PersonResource;
import com.pedro.restapi.rest.errors.DepartmentErrorException;
import com.pedro.restapi.rest.errors.PersonErrorException;
import com.pedro.restapi.service.dto.DepartmentDTO;
import com.pedro.restapi.service.dto.PersonDTO;
import com.pedro.restapi.service.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    PersonRepository personRepository;

    PersonMapper personMapper;

    DepartmentRepository departmentRepository;

    private final Logger log = LoggerFactory.getLogger(PersonService.class);


    public PersonService(PersonRepository personRepository, DepartmentRepository departmentRepository) {
        super();
        this.personRepository = personRepository;
        this.departmentRepository = departmentRepository;
    }

    public Person save(Person person) {
        log.info("Saving new person");
        personRepository.save(person);
        log.info("Person {} saved with success", person.getName());
        return person;
    }


    public Person update(Long id, Person Updatedperson) throws DepartmentErrorException {
        log.info("Updating person...");

        Person person = personRepository.findById(id)
                .orElseThrow(
                        () -> new PersonErrorException("Person not found")
        );

        person.setName(Updatedperson.getName());
        Department newDepartment = departmentRepository.findById(Updatedperson.getDepartment().getId())
                .orElseThrow(
                        () -> new DepartmentErrorException("Department not found")
                );

        person.setDepartment(newDepartment);

        log.info("Person updated!");
        return personRepository.save(person);

    }


    public void delete(Long id) {
        log.info("Deleting person...");
        personRepository.deleteById(id);
        log.info("Person deleted!");
    }


    public List<PersonDTO> getPeopleList() {
        List<Person> people = personRepository.findAll();

        return people.stream()
                .map(person -> {
                    float totalDuration = (float) person.getTasks()
                            .stream()
                            .mapToDouble(task -> task.getDuration().toHours())
                            .sum();

                    PersonDTO personDTO = new PersonDTO();
                    personDTO.setName(person.getName());
                    personDTO.setDepartmentName(person.getDepartment().getTitle());
                    personDTO.setTotalTaskTime(totalDuration);

                    return personDTO;
                })
                .collect(Collectors.toList());
    }

//    public List<DepartmentDTO> getDepartmentsWithCounts() {
//        return Departmen.findDepartments();
//    }

}
