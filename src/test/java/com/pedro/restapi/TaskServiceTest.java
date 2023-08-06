package com.pedro.restapi;

import com.pedro.restapi.domain.Department;
import com.pedro.restapi.domain.Person;
import com.pedro.restapi.domain.Task;
import com.pedro.restapi.repository.PersonRepository;
import com.pedro.restapi.repository.TaskRepository;
import com.pedro.restapi.rest.errors.TaskErrorException;
import com.pedro.restapi.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    private final Logger log = LoggerFactory.getLogger(PersonServiceTest.class);

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTask() {

        log.info("...Testing save task...");
        Task taskToAdd = new Task();
        taskToAdd.setTitle("Tarefa 1");
        taskToAdd.setDescription("Descrição aleatoria 1");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("Tarefa 2");
        savedTask.setDescription("Descrição aleatoria 2");

        when(taskRepository.save(taskToAdd)).thenReturn(savedTask);

        Task addedTask = taskService.add(taskToAdd);

        assertEquals(savedTask.getId(), addedTask.getId());
        assertEquals(savedTask.getTitle(), addedTask.getTitle());
        assertEquals(savedTask.getDescription(), addedTask.getDescription());
        verify(taskRepository, times(1)).save(taskToAdd);

        log.info("Test finished!");
    }

    @Test
    public void testAllocatePersonToTask() throws TaskErrorException {

        log.info("...Testing allocate person to task...");


        Long taskId = 1L;
        Long personId = 1L;

        Department department = new Department();
        department.setId(1L);
        department.setTitle("TI");

        Task task = new Task();
        task.setId(taskId);
        task.setTitle("Tarefa 1");
        task.setDepartment(department);

        Person person = new Person();
        person.setId(personId);
        person.setName("Charles Xavier");
        person.setDepartment(department);

        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(task));

        when(personRepository.findById(personId)).thenReturn(java.util.Optional.of(person));

        Task allocatedTask = taskService.allocate(taskId, personId);

        assertEquals(person, allocatedTask.getPerson());
        verify(taskRepository, times(1)).save(task);
        log.info("Test finished!");
    }

    @Test
    public void testAllocateTaskNotFound() {
        log.info("...Testing allocate person to task not found...");


        Long taskId = 1L;
        Long personId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.empty());

        assertThrows(TaskErrorException.class, () -> taskService.allocate(taskId, personId));
        log.info("Test finished!");

    }

    @Test
    public void testAllocatePersonNotFound() {
        log.info("...Testing allocate person not found to task...");

        Long taskId = 1L;
        Long personId = 1L;

        Task task = new Task();
        task.setId(taskId);
        task.setTitle("Tarefa 1");
        task.setDepartment(new Department());

        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(task));

        when(personRepository.findById(personId)).thenReturn(java.util.Optional.empty());

        assertThrows(TaskErrorException.class, () -> taskService.allocate(taskId, personId));
        log.info("Test finished!");

    }

    @Test
    public void testAllocatePersonFromDifferentDepartment() {
        log.info("...Testing allocate person from different dept. to task...");


        Long taskId = 1L;
        Long personId = 1L;

        Task task = new Task();
        task.setId(taskId);
        task.setTitle("Task 1");
        Department taskDepartment = new Department();
        taskDepartment.setId(1L);
        taskDepartment.setTitle("TI");
        task.setDepartment(taskDepartment);

        Person person = new Person();
        person.setId(personId);
        person.setName("Storm");
        Department personDepartment = new Department();
        personDepartment.setId(2L);
        personDepartment.setTitle("DIR");
        person.setDepartment(personDepartment);

        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(task));

        when(personRepository.findById(personId)).thenReturn(java.util.Optional.of(person));

        assertThrows(TaskErrorException.class, () -> taskService.allocate(taskId, personId));
        log.info("Test finished!");

    }


    @Test
    public void testFinalizeTask() throws TaskErrorException {

        log.info("...Testing finalize task...");

        Long taskId = 1L;

        Task task = new Task();
        task.setId(taskId);
        task.setTitle("Tarefa 1");
        task.setFinished(false);

        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(task));

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task finalizedTask = taskService.finalize(taskId);

        assertTrue((boolean) finalizedTask.getFinished());
        verify(taskRepository, times(1)).save(task);
        log.info("Test finished!");


    }

    @Test
    public void testFinalizeTaskNotFound() {

        log.info("...Testing finalize task not found...");

        Long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.empty());

        assertThrows(TaskErrorException.class, () -> taskService.finalize(taskId));
        log.info("Test finished!");

    }


    @Test
    public void testGetPendingTasks() {

        log.info("...Testing pending tasks...");


        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Tarefa 1");
        task1.setPerson(null);
        task1.setDueDate(LocalDate.from(LocalDateTime.now().plusDays(1)));

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Tarefa 2");
        task2.setPerson(null);
        task2.setDueDate(LocalDate.from(LocalDateTime.now().plusDays(2)));

        Task task3 = new Task();
        task3.setId(3L);
        task3.setTitle("Tarefa 3");
        task3.setPerson(null);
        task3.setDueDate(LocalDate.from(LocalDateTime.now().plusDays(3)));

        List<Task> pendingTasks = List.of(task1, task2, task3);

        when(taskRepository.findTop3ByPersonIsNullOrderByDueDateAsc()).thenReturn(pendingTasks);

        List<Task> result = taskService.getPendingTasks();

        assertEquals(3, result.size());
        assertEquals(task1, result.get(0));
        assertEquals(task2, result.get(1));
        assertEquals(task3, result.get(2));
        verify(taskRepository, times(1)).findTop3ByPersonIsNullOrderByDueDateAsc();
        log.info("Test finished!");

    }



}
