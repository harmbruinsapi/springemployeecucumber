package com.invenna.testcode.employee.models;

import static com.invenna.testcode.employee.constants.EmployeeConstants.FREE_TEXT_REGEX;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Department {

  long id;
  @NotNull
  @Pattern(message = "Name contains invalid characters", regexp = FREE_TEXT_REGEX)
  String name;
}
