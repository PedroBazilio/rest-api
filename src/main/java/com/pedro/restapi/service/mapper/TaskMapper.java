package com.pedro.restapi.service.mapper;

import com.pedro.restapi.domain.Task;
import com.pedro.restapi.service.dto.TaskDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {})
public interface TaskMapper extends EntityMapper<TaskDTO, Task>{

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "person.id", target = "personId")
    TaskDTO toDto(Task task);

    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "personId", target = "person")
    Task toEntity(TaskDTO taskDTO);


}
