package com.pedro.restapi.service.dto;

import java.util.OptionalDouble;

public class PersonAvgTimeTaskDTO{

    private String name;

    private String departmentName;

    private OptionalDouble avgTaskTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public OptionalDouble getAvgTaskTime() {
        return avgTaskTime;
    }

    public void setAvgTaskTime(OptionalDouble avgTaskTime) {
        this.avgTaskTime = avgTaskTime;
    }

}
