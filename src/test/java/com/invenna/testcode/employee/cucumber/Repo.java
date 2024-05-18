package com.invenna.testcode.employee.cucumber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.invenna.testcode.employee.entities.DepartmentDbWrapper;
import com.invenna.testcode.employee.models.Employee;

import java.util.List;

@Repository
public interface Repo extends JpaRepository<DepartmentDbWrapper, Long> {
  @Query(value = "SELECT * FROM EMPLOYEE", nativeQuery = true)
  List<String> findAllEmployees();

  @Query(value = "SELECT * FROM DEPARTMENT", nativeQuery = true)
  List<String> findAllDepartments();
}
