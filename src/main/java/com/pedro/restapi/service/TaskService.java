package com.pedro.restapi.service;

import com.pedro.restapi.domain.Person;
import com.pedro.restapi.domain.Task;
import com.pedro.restapi.repository.DepartmentRepository;
import com.pedro.restapi.repository.PersonRepository;
import com.pedro.restapi.repository.TaskRepository;
import com.pedro.restapi.rest.errors.TaskErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final Logger log = LoggerFactory.getLogger(PersonService.class);

    PersonRepository personRepository;

    TaskRepository taskRepository;

    DepartmentRepository departmentRepository;
    public TaskService(PersonRepository personRepository, DepartmentRepository departmentRepository, TaskRepository taskRepository) {
        super();
        this.personRepository = personRepository;
        this.departmentRepository = departmentRepository;
        this.taskRepository = taskRepository;
    }


    public Task add(Task task) {
        log.info("Adding task {}", task.getTitle());
        return taskRepository.save(task);
    }


    public Task allocate(Long id, Long personId) throws TaskErrorException {
        log.info("Attempting to allocate person to a task");
        Task task = taskRepository.findById(id).
                orElseThrow(
                        () -> new TaskErrorException("Could not find task")
                );

        Person personTobeAllocated = personRepository.findById(personId).
                orElseThrow(
                        () -> new TaskErrorException("Could not find person")
                );

        if (!personTobeAllocated.getDepartment().getId().equals(task.getDepartment().getId())) {
            throw new TaskErrorException("Person isn't from the same department");
        }

        task.setPerson(personTobeAllocated);
        taskRepository.save(task);
        log.info("Person allocated with success!!");
        return task;

    }

    public Task finalize(Long id) throws TaskErrorException {
        log.info("Attempting to finalize task");
        Task finishedTask = taskRepository.findById(id).orElseThrow(
                () -> new TaskErrorException("Task not found")
        );

        finishedTask.setFinished(true);
        log.info("Task finalized with success!!");
        return taskRepository.save(finishedTask);

    }

    public List<Task> getPendingTasks() {
        log.info("Getting pending tasks");
        return taskRepository
                .findTop3ByPersonIsNullOrderByDueDateAsc();
    }

}
