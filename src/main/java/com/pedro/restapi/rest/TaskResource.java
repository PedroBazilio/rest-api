package com.pedro.restapi.rest;


import com.pedro.restapi.domain.Task;
import com.pedro.restapi.rest.errors.TaskErrorException;
import com.pedro.restapi.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/tarefas")
public class TaskResource {

    private final Logger log = LoggerFactory.getLogger(TaskResource.class);

    private final TaskService taskService;

    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        log.info("Rest request to add a task");
        return ok(taskService.add(task));
    }

    @PutMapping("/alocar/{id}")
    public ResponseEntity<Task> allocateTask (@PathVariable Long id, @RequestBody Map<String, Object> personIdToAllocate) throws TaskErrorException {
        log.info("Rest request to allocate a task");
        Long personId = Long.parseLong(personIdToAllocate.get("person_id").toString());
        return ResponseEntity.ok(taskService.allocate(id, personId));
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

    @GetMapping("/gastos")
    public ResponseEntity<List<Task>> getAvgTimeTasks() {
        log.info("Rest request to get pending tasks");
        List<Task> pendingTasks = taskService.getPendingTasks();
        return ResponseEntity.ok(pendingTasks);
    }
}
