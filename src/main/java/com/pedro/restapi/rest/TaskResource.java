package com.pedro.restapi.rest;

import com.pedro.restapi.domain.Person;
import com.pedro.restapi.domain.Task;
import com.pedro.restapi.repository.TaskRepository;
import com.pedro.restapi.rest.errors.TaskErrorException;
import com.pedro.restapi.service.TaskService;
import com.pedro.restapi.service.dto.PersonDTO;
import com.pedro.restapi.service.dto.TaskDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/tarefas")
public class TaskResource {

    private final Logger log = LoggerFactory.getLogger(TaskResource.class);

    private TaskService taskService;

    private TaskRepository taskRepository;

    public TaskResource(TaskService taskService, TaskRepository taskRepository) {

        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }


    @PostMapping("")
    public ResponseEntity<Task> addTask(Task task) throws URISyntaxException {
        log.info("Rest request to add a task");
        return ok(taskService.add(task));
    }

    @PutMapping("/alocar/{id}")
    public ResponseEntity<Task> allocateTask (@PathVariable Long id, Person person) throws TaskErrorException {
        log.info("Rest request to allocate a task");
        return ResponseEntity.ok(taskService.allocate(id, person));

    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<Task> finalizeTask(@PathVariable Long id) throws TaskErrorException {
        log.info("Rest request to finalize a task");
        Task finalizedTask = taskService.finalize(id);
        return ResponseEntity.ok(finalizedTask);

    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<Task>> getPendingTasks() {
        log.info("Rest request to get pending tasks");
        List<Task> pendingTasks = taskService.getPendingTasks();
        return ResponseEntity.ok(pendingTasks);
    }




}
