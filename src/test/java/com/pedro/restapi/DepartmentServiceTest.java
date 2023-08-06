package com.pedro.restapi;

import com.pedro.restapi.domain.Department;
import com.pedro.restapi.repository.DepartmentRepository;
import com.pedro.restapi.service.DepartmentService;
import com.pedro.restapi.service.dto.DepartmentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private final Logger log = LoggerFactory.getLogger(PersonServiceTest.class);


    @Test
    public void testGetDepartmentsDTOList() {
        log.info("...Testing departmentDTO list...");
        Department department1 = new Department();
        department1.setTitle("IT");
        Department department2 = new Department();
        department2.setTitle("HR");

        // Resultado esperado
        List<DepartmentDTO> expectedList = Arrays.asList(
                new DepartmentDTO("TI", 5L, 10L),
                new DepartmentDTO("DIR", 3L, 6L)
        );

        // Configuração do mock para simular a resposta da consulta
        when(departmentRepository.getDepartmentsDTOList()).thenReturn(expectedList);

        // Execução do método a ser testado
        List<DepartmentDTO> resultList = departmentService.getDepartmentsDTOList();

        // Verificações
        assertEquals(expectedList.size(), resultList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getTitle(), resultList.get(i).getTitle());
            assertEquals(expectedList.get(i).getPeople(), resultList.get(i).getPeople());
            assertEquals(expectedList.get(i).getTasks(), resultList.get(i).getTasks());
        }

        verify(departmentRepository, times(1)).getDepartmentsDTOList();
        log.info("Test finished!");
    }

}
