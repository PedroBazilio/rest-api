package com.pedro.restapi.service.mapper;

import com.pedro.restapi.domain.Person;
import com.pedro.restapi.service.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {

    @Mapping(source = "department.id", target = "departmentId")
    PersonDTO toDTO(Person person);

    @Mapping(source = "departmentId", target = "department")
    Person toEntity(PersonDTO personDTO);

}
