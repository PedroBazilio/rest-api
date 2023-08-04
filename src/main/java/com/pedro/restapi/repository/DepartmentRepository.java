package com.pedro.restapi.repository;

import com.pedro.restapi.domain.Department;
import com.pedro.restapi.service.dto.DepartmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{


    @Query("SELECT NEW com.pedro.restapi.service.dto.DepartmentDTO(d.title, COUNT(DISTINCT p), COUNT(distinct t)) " +
            "FROM Department d " +
            "LEFT JOIN Person p ON d.id = p.department.id " +
            "LEFT JOIN Task t ON d.id = t.department.id " +
            "GROUP BY d.title")
    List<DepartmentDTO> getDepartmentsDTOList();


}
