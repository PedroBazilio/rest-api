package com.pedro.restapi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@RequiredArgsConstructor
public class DepartmentDTO {

    private String title;

    private Long people;

    private Long tasks;

}
