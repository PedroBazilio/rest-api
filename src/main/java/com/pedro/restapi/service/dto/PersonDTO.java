package com.pedro.restapi.service.dto;

import com.pedro.restapi.domain.Task;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

public class PersonDTO {

    private static final long serialVersionUID = -6775833611650081935L;

    private String name;

    private String departmentName;

    private float avgTaskTime;

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

    public float getAvgTaskTime() {
        return avgTaskTime;
    }

    public void setAvgTaskTime(float avgTaskTime) {
        this.avgTaskTime = avgTaskTime;
    }

    public float getTotalTaskTime() {
        return totalTaskTime;
    }

    public void setTotalTaskTime(float totalTaskTime) {
        this.totalTaskTime = totalTaskTime;
    }
}
