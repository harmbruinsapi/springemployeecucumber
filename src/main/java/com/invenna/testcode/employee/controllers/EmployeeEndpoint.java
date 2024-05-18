package com.invenna.testcode.employee.controllers;

import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/employee")
@RestController
@Validated
public class EmployeeEndpoint {

  private final EmployeeService employeeService;

  @GetMapping("/{id}")
  public ResponseEntity<Employee> retrieveEmployee(@PathVariable("id") long id) {
    return ResponseEntity.ok(employeeService.retrieveEmployeeById(id));
  }

  @PostMapping
  public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) {
    return new ResponseEntity<>(employeeService.create(employee), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Employee> update(@PathVariable("id") long id,
      @RequestBody Employee employee) {
    return ResponseEntity.ok(employeeService.update(id, employee));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
    employeeService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
