package com.pedro.restapi.rest;

import com.pedro.restapi.domain.Department;
import com.pedro.restapi.repository.DepartmentRepository;
import com.pedro.restapi.service.PersonService;
import com.pedro.restapi.service.dto.DepartmentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartmentResource {

    private final DepartmentRepository departmentRepository;

    PersonService personService;

    private final Logger log = LoggerFactory.getLogger(DepartmentResource.class);

    public DepartmentResource(DepartmentRepository departmentRepository, PersonService personService) {
        this.departmentRepository = departmentRepository;
        this.personService = personService;
    }


}
