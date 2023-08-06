package com.pedro.restapi.service;

import com.pedro.restapi.domain.Department;
import com.pedro.restapi.domain.Person;
import com.pedro.restapi.repository.DepartmentRepository;
import com.pedro.restapi.repository.PersonRepository;
import com.pedro.restapi.rest.errors.DepartmentErrorException;
import com.pedro.restapi.rest.errors.PersonErrorException;
import com.pedro.restapi.service.dto.PersonAvgTimeTaskDTO;
import com.pedro.restapi.service.dto.PersonTotalTimeTaskDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class PersonService {

    PersonRepository personRepository;

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


    public Person update(Long id, Person updatedPerson) throws DepartmentErrorException {
        log.info("Updating person...");

        Person person = personRepository.findById(id)
                .orElseThrow(
                        () -> new PersonErrorException("Person not found")
        );

        person.setName(updatedPerson.getName());
        if(updatedPerson.getDepartment() != null) {
            Department newDepartment = departmentRepository.findById(updatedPerson.getDepartment().getId())
                    .orElseThrow(
                            () -> new DepartmentErrorException("Department not found")
                    );

            person.setDepartment(newDepartment);
        }

        log.info("Person updated!");
        Person personUpdated = personRepository.save(person);
        return personUpdated;

    }


    public void delete(Long id) {
        log.info("Deleting person...");
        personRepository.deleteById(id);
        log.info("Person deleted!");
    }


    public List<PersonTotalTimeTaskDTO> getPeopleList() {
        List<Person> people = personRepository.findAll();

        return people.stream()
                .map(person -> {
                    float totalDuration = (float) person.getTasks()
                            .stream()
                            .mapToDouble(personTask -> personTask.getDuration().doubleValue())
                            .sum();

                    PersonTotalTimeTaskDTO personTotalTimeTaskDTO = new PersonTotalTimeTaskDTO();
                    personTotalTimeTaskDTO.setName(person.getName());
                    personTotalTimeTaskDTO.setDepartmentName(person.getDepartment().getTitle());
                    personTotalTimeTaskDTO.setTotalTaskTime(totalDuration);

                    return personTotalTimeTaskDTO;
                })
                .collect(Collectors.toList());
    }

    public List<PersonAvgTimeTaskDTO> getPeopleListAvgTime() {
        List<Person> people = personRepository.findAll();

        return people.stream()
                .map(person -> {
                    OptionalDouble avgDuration = person.getTasks()
                            .stream()
                            .mapToInt(personTask -> personTask.getDuration().intValue())
                            .average();

                    PersonAvgTimeTaskDTO personAvgTimeTaskDTO = new PersonAvgTimeTaskDTO();
                    personAvgTimeTaskDTO.setName(person.getName());
                    personAvgTimeTaskDTO.setDepartmentName(person.getDepartment().getTitle());
                    personAvgTimeTaskDTO.setAvgTaskTime(avgDuration);

                    return personAvgTimeTaskDTO;
                })
                .collect(Collectors.toList());

    }

}
