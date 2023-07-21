package com.pedro.restapi.rest;

import com.pedro.restapi.domain.Department;
import com.pedro.restapi.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departamento")
public class DepartmentResource {

    private final DepartmentRepository departmentRepository;

    private final Logger log = LoggerFactory.getLogger(DepartmentResource.class);

    public DepartmentResource(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }





}
