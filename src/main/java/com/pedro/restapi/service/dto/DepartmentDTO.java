package com.pedro.restapi.service.dto;

public class DepartmentDTO {

    private static final long serialVersionUID = -6775833611650081935L;

    private String title;

    private Integer people;

    private Integer tasks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Integer getTasks() {
        return tasks;
    }

    public void setTasks(Integer tasks) {
        this.tasks = tasks;
    }
}
