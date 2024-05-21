package com.invenna.testcode.employee.unittest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;

import com.invenna.testcode.employee.controllers.EmployeeEndpoint;
import com.invenna.testcode.employee.controllers.SearchEndpoint;
import com.invenna.testcode.employee.models.Department;
import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.EmployeeStatus;
import com.invenna.testcode.employee.models.SalaryRange;
import com.invenna.testcode.employee.models.Search;
import com.invenna.testcode.employee.service.EmployeeSearchFactory;
import com.invenna.testcode.employee.service.EmployeeService;
import com.invenna.testcode.employee.service.SearchService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc 
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
@TestPropertySource(locations = "classpath:application-persistent.yml")
@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {SearchEndpoint.class, EmployeeSearchFactory.class, EmployeeSearchFactory.class, EmployeeEndpoint.class, SearchService.class})
public class SearchControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SearchService service;

    @MockBean
    private EmployeeService employeeService;

    @MockBean 
    private EmployeeSearchFactory employeeSearchFactory;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test_When_SearchEmployee_is_active() throws Exception {
    // Some test data
        Department department = Department.builder()
        .name("IT Department")
        .build();

    // Some test data
    Search search = Search.builder()
        .employeeName("Alex")
        .build();

    // Some test data
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(Employee.builder()
        .id(1)
        .name("Alex")
        .department(department)
        .employeeStatus(EmployeeStatus.ACTIVE)
        .salary(BigDecimal.valueOf(1000))
        .joiningDate(LocalDate.now())
        .build());

    // Mocking the service
    when(employeeSearchFactory.search(Mockito.any())).thenReturn(employeeList);

    // Perform test
    mvc.perform(post("/employee/search")
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.toJson(search)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name", is("Alex")))
        .andExpect(jsonPath("$[0].employeeStatus", is("ACTIVE")));
    }

    @Test
    public void test_When_SearchEmployee_is_on_leave() throws Exception {
    // Some test data
        Department department = Department.builder()
        .name("IT Department")
        .build();

    // Some test data
    Search search = Search.builder()
        .employeeName("Alex")
        .build();

    // Some test data
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(Employee.builder()
        .id(1)
        .name("Alex")
        .department(department)
        .employeeStatus(EmployeeStatus.ON_LEAVE)
        .salary(BigDecimal.valueOf(1000))
        .joiningDate(LocalDate.now())
        .build());

    // Mocking the service
    when(employeeSearchFactory.search(Mockito.any())).thenReturn(employeeList);

    // Perform test
    mvc.perform(post("/employee/search")
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.toJson(search)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name", is("Alex")))
        .andExpect(jsonPath("$[0].employeeStatus", is("ON_LEAVE")));
    }

    @Test
    public void test_When_SearchEmployee_is_terminated() throws Exception {
    // Some test data
        Department department = Department.builder()
        .name("IT Department")
        .build();

    // Some test data
    Search search = Search.builder()
        .employeeName("Alex")
        .build();

    // Some test data
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(Employee.builder()
        .id(1)
        .name("Alex")
        .department(department)
        .employeeStatus(EmployeeStatus.TERMINATED)
        .salary(BigDecimal.valueOf(1000))
        .joiningDate(LocalDate.now())
        .build());

    // Mocking the service
    when(employeeSearchFactory.search(Mockito.any())).thenReturn(employeeList);

    // Perform test
    mvc.perform(post("/employee/search")
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.toJson(search)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name", is("Alex")))
        .andExpect(jsonPath("$[0].employeeStatus", is("TERMINATED")));
    }

    @Test
    public void test_When_SearchEmployee_is_in_salary_range() throws Exception {
    // Some test data
        Department department = Department.builder()
        .name("IT Department")
        .build();
    
    // Some test data
    Search search = Search.builder()
        .employeeName("Alex")
        .salaryRange(new SalaryRange(BigDecimal.valueOf(800), BigDecimal.valueOf(1200)))
        .build();

    // Some test data
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(Employee.builder()
        .id(1)
        .name("Alex")
        .department(department)
        .employeeStatus(EmployeeStatus.ACTIVE)
        .salary(BigDecimal.valueOf(1000))
        .joiningDate(LocalDate.now())
        .build());

    // Mocking the service
    when(employeeSearchFactory.search(Mockito.any())).thenReturn(employeeList);

    // Perform test
    mvc.perform(post("/employee/search")
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtil.toJson(search)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name", is("Alex")))
        .andExpect(jsonPath("$[0].employeeStatus", is("ACTIVE")));
    }



    
}