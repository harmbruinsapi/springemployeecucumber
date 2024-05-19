package com.invenna.testcode.employee.unittest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;

import com.invenna.testcode.employee.controllers.EmployeeEndpoint;
import com.invenna.testcode.employee.models.Department;
import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.EmployeeStatus;
import com.invenna.testcode.employee.service.EmployeeService;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = EmployeeEndpoint.class)
@AutoConfigureMockMvc 
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
@TestPropertySource(locations = "classpath:application.yml")
@AutoConfigureTestDatabase
public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test_WhenPostEmployee_thenCreateEmployee() throws Exception {
        Department department = Department.builder()
        .name("IT Department")
        .build();

    // Some test data
    Employee employee = Employee.builder()
        .name("Alex")
        .department(department)
        .salary(new BigDecimal("50000.00"))
        .employeeStatus(EmployeeStatus.ACTIVE)
        .joiningDate(LocalDate.now())
        .build();

    given(service.create(Mockito.any())).willReturn(employee);

    mvc.perform(post("/employee")
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.toJson(employee)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", is("Alex")));

    verify(service, VerificationModeFactory.times(1)).create(Mockito.any());
    reset(service);
    }
}