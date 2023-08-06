package com.pedro.restapi;

import com.pedro.restapi.domain.Department;
import com.pedro.restapi.domain.Person;
import com.pedro.restapi.domain.Task;
import com.pedro.restapi.repository.DepartmentRepository;
import com.pedro.restapi.repository.PersonRepository;
import com.pedro.restapi.rest.errors.DepartmentErrorException;
import com.pedro.restapi.rest.errors.PersonErrorException;
import com.pedro.restapi.service.PersonService;
import com.pedro.restapi.service.dto.PersonAvgTimeTaskDTO;
import com.pedro.restapi.service.dto.PersonTotalTimeTaskDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PersonServiceTest {

    private final Logger log = LoggerFactory.getLogger(PersonServiceTest.class);

    @Mock
    private PersonRepository personRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private PersonService personService;

    Person person;

    Department department;

    @BeforeEach
    void init() {

        log.info("Starting test");
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testSavePerson() {
        log.info("...Testing save person...");

        department = new Department();
        department.setId(1L);
        department.setTitle("TI");

        person = new Person();
        person.setId(1L);
        person.setName("Pedro");
        person.setDepartment(department);

        // Chame o método a ser testado
        Person savedPerson = personService.save(person);

        // Verifica se o método save foi chamado no repositório com a pessoa correta
        verify(personRepository, times(1)).save(person);

        // Verifica se a pessoa retornada possui um ID válido
        assertNotNull(savedPerson.getId());
        log.info("Test finished!");
    }


    @Test
    public void testUpdatePerson() throws DepartmentErrorException {

        log.info("...Testing update person...");

        Long id = 1L;
        Long id2 = 2L;
        Department existingDepartment = new Department();
        existingDepartment.setId(id);
        existingDepartment.setTitle("TI");

        Department newDepartment = new Department();
        newDepartment.setId(id);
        newDepartment.setTitle("DIR");

        Person updatedPerson = new Person();
        updatedPerson.setName("Marta");
        updatedPerson.setDepartment(newDepartment);

        Person existingPerson = new Person();
        existingPerson.setId(id);
        existingPerson.setName("Pelé");
        existingPerson.setDepartment(existingDepartment);

        when(personRepository.findById(id)).thenReturn(java.util.Optional.of(existingPerson));
        when(personRepository.save(any(Person.class))).thenReturn(updatedPerson);

        when(departmentRepository.findById(existingDepartment.getId()))
                .thenReturn(java.util.Optional.of(existingDepartment));

        Person updated = personService.update(id, updatedPerson);

        assertEquals(updatedPerson.getName(), updated.getName());
        assertEquals(newDepartment, updated.getDepartment());
        verify(personRepository, times(1)).save(existingPerson);
        verify(personRepository, times(1)).findById(id);
        verify(departmentRepository, times(1)).findById(existingDepartment.getId());

        log.info("Test finished!");

    }

    @Test
    public void testUpdatePersonNotFound() {
        log.info("...Testing person not found...");

        Long id = 1L;
        Person updatedPerson = new Person();
        updatedPerson.setName("Jorge");

        when(personRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(PersonErrorException.class, () -> personService.update(id, updatedPerson));
        log.info("Test finished!");

    }

    @Test
    public void testDeletePerson() {
        log.info("...Testing delete person...");

        Long id = 1L;

        personService.delete(id);

        verify(personRepository, times(1)).deleteById(id);
        log.info("Test finished!");

    }

    @Test
    public void testDeletePersonNotFound() {
        log.info("...Testing delete person not found...");

        Long id = 1L;

        doThrow(PersonErrorException.class).when(personRepository).deleteById(id);

        assertThrows(PersonErrorException.class, () -> personService.delete(id));
        log.info("Test finished!");

    }


    @Test
    public void testGetPeopleTotalTimeList() {
        log.info("...Testing get people total time list...");

        Person person1 = new Person();
        person1.setId(1L);
        person1.setName("Ripley");

        Department department1 = new Department();
        department1.setId(1L);
        department1.setTitle("TI");

        person1.setDepartment(department1);

        Set<Task> tasksList1 = new HashSet<>();

        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("tarefa 1");
        task1.setDuration(BigDecimal.valueOf(5.0));

        tasksList1.add(task1);

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("tarefa 2");
        task2.setDuration(BigDecimal.valueOf(3.0));

        tasksList1.add(task2);

        person1.setTasks(tasksList1);

        Person person2 = new Person();
        person2.setId(2L);
        person2.setName("Ricky");

        Department department2 = new Department();
        department2.setId(2L);
        department2.setTitle("SOS");
        person2.setDepartment(department2);

        Set<Task> tasksList2 = new HashSet<>();

        Task task3 = new Task();
        task3.setId(3L);
        task3.setTitle("tarefa 3");
        task3.setDuration(BigDecimal.valueOf(2.0));

        tasksList2.add(task3);

        person2.setTasks(tasksList2);

        List<Person> people = List.of(person1, person2);

        when(personRepository.findAll()).thenReturn(people);

        List<PersonTotalTimeTaskDTO> result = personService.getPeopleList();

        assertEquals(2, result.size());

        PersonTotalTimeTaskDTO dto1 = result.get(0);
        assertEquals("Ripley", dto1.getName());
        assertEquals("TI", dto1.getDepartmentName());
        assertEquals(8.0f, dto1.getTotalTaskTime(), 0.001f);

        PersonTotalTimeTaskDTO dto2 = result.get(1);
        assertEquals("Ricky", dto2.getName());
        assertEquals("SOS", dto2.getDepartmentName());
        assertEquals(2.0f, dto2.getTotalTaskTime(), 0.001f);
        log.info("Test finished!");

    }

    @Test
    public void testGetPeopleListAvgTime() {

        log.info("...Testing get people avg time list...");

        Person person1 = new Person();
        person1.setId(1L);
        person1.setName("Mario Jorge");

        Department department1 = new Department();
        department1.setId(1L);
        department1.setTitle("TI");
        person1.setDepartment(department1);

        Set<Task> tasks1 = new HashSet<>();

        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");
        task1.setDuration(BigDecimal.valueOf(5.0));
        tasks1.add(task1);

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");
        task2.setDuration(BigDecimal.valueOf(3.0));

        tasks1.add(task2);

        person1.setTasks(tasks1);

        Person person2 = new Person();
        person2.setId(2L);
        person2.setName("Alan Turing");

        Department department2 = new Department();
        department2.setId(2L);
        department2.setTitle("DIR");

        person2.setDepartment(department2);

        Set<Task> tasks2 = new HashSet<>();

        Task task3 = new Task();
        task3.setId(3L);
        task3.setTitle("Task 3");
        task3.setDuration(BigDecimal.valueOf(2.0));
        tasks2.add(task3);
        person2.setTasks(tasks2);

        List<Person> people = List.of(person1, person2);

        // Configuração do mock para retornar a lista de pessoas
        when(personRepository.findAll()).thenReturn(people);

        // Execução do método a ser testado
        List<PersonAvgTimeTaskDTO> result = personService.getPeopleListAvgTime();

        // Verificações
        assertEquals(2, result.size());

        PersonAvgTimeTaskDTO dto1 = result.get(0);
        assertEquals("Mario Jorge", dto1.getName());
        assertEquals("TI", dto1.getDepartmentName());
        assertTrue(dto1.getAvgTaskTime().isPresent());
        assertEquals(4.0, dto1.getAvgTaskTime().getAsDouble(), 0.001);

        PersonAvgTimeTaskDTO dto2 = result.get(1);
        assertEquals("Alan Turing", dto2.getName());
        assertEquals("DIR", dto2.getDepartmentName());
        assertTrue(dto2.getAvgTaskTime().isPresent());
        assertEquals(2.0, dto2.getAvgTaskTime().getAsDouble(), 0.001);

        log.info("Test finished!");
    }
}
