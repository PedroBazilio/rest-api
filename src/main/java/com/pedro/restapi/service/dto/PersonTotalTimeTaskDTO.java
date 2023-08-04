package com.pedro.restapi.service.dto;

public class PersonTotalTimeTaskDTO{

    private String name;
    
    private String departmentName;

    private float totalTaskTime;

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


    public float getTotalTaskTime() {
        return totalTaskTime;
    }

    public void setTotalTaskTime(float totalTaskTime) {
        this.totalTaskTime = totalTaskTime;
    }
}
