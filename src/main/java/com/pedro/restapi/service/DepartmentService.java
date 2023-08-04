package com.pedro.restapi.service;

import com.pedro.restapi.domain.Department;
import com.pedro.restapi.repository.DepartmentRepository;
import com.pedro.restapi.repository.PersonRepository;
import com.pedro.restapi.repository.TaskRepository;
import com.pedro.restapi.service.dto.DepartmentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    DepartmentRepository departmentRepository;

    PersonRepository personRepository;

    TaskRepository taskRepository;
    private final Logger log = LoggerFactory.getLogger(DepartmentService.class);

    public DepartmentService(DepartmentRepository departmentRepository,
                             PersonRepository personRepository,
                             TaskRepository taskRepository) {
        this.departmentRepository = departmentRepository;
        this.personRepository = personRepository;
        this.taskRepository = taskRepository;

    }

    public List<DepartmentDTO> getDepartmentsDTOList() {
        log.info("Getting departmentDTO list...");
       return departmentRepository.getDepartmentsDTOList();
    }

}
