package com.pedro.restapi.rest;

import com.pedro.restapi.service.DepartmentService;
import com.pedro.restapi.service.dto.DepartmentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartmentResource {

    DepartmentService departmentService;

    private final Logger log = LoggerFactory.getLogger(DepartmentResource.class);

    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("")
    public ResponseEntity<List<DepartmentDTO>> getDepartments() {
        log.info("Rest request to get departments");
        List<DepartmentDTO> departmentsDTOList = departmentService.getDepartmentsDTOList();
        return ResponseEntity.ok(departmentsDTOList);
    }


}
