package com.pedro.restapi.service.dto;

import com.pedro.restapi.domain.Task;

import java.time.LocalDateTime;
import java.util.Set;

public class PersonDTO {

    private static final long serialVersionUID = -6775833611650081935L;

    private Long id;

    private String name;

    private Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    private Set<Task> tasks;

}
