// package com.invenna.testcode.employee.unittest;

// import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
// import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.invenna.testcode.employee.controllers.EmployeeEndpoint;
// import com.invenna.testcode.employee.controllers.SearchEndpoint;
// import com.invenna.testcode.employee.entities.EmployeeDbWrapper;
// import com.invenna.testcode.employee.models.Department;
// import com.invenna.testcode.employee.models.Employee;
// import com.invenna.testcode.employee.models.EmployeeStatus;
// import com.invenna.testcode.employee.models.Search;
// import com.invenna.testcode.employee.repositories.DbConvertor;
// import com.invenna.testcode.employee.repositories.EmployeeRepository;
// import com.invenna.testcode.employee.service.impl.SearchByEmployeeStatusService;
// import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
// import org.springframework.test.context.TestPropertySource;
// import org.springframework.test.context.junit4.SpringRunner;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import java.math.BigDecimal;
// import java.time.LocalDate;
// import java.util.Arrays;
// import java.util.Collection;
// import java.util.List;

// @RunWith(SpringRunner.class)
// @AutoConfigureMockMvc 
// @EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
// @TestPropertySource(locations = "classpath:application-persistent.yml")
// @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {EmployeeEndpoint.class, SearchEndpoint.class, SearchByEmployeeStatusService.class})
// class SearchByEmployeeStatusServiceTest {

//     @InjectMocks
//     private SearchByEmployeeStatusService searchByEmployeeStatusService;

//     @Mock
//     private EmployeeRepository employeeRepository;

//     @Mock
//     private DbConvertor dbConvertor;

//     @Test
//     void testSearchEmployees() {
//         // Arrange
//         EmployeeDbWrapper employeeDbWrapper = new EmployeeDbWrapper();
//         // Set the properties of the employeeDbWrapper...

//         Employee employee = Employee.builder()
//             .id(1)
//             .name("Alex")
//             .department(Department.builder().id(1).name("HR").build())
//             .salary(new BigDecimal("50000.00"))
//             .employeeStatus(EmployeeStatus.ACTIVE)
//             .joiningDate(LocalDate.now())
//             .build();

//         List<EmployeeDbWrapper> employeeDbWrappers = Arrays.asList(employeeDbWrapper);

//         when(employeeRepository.findAllByEmployeeStatus(EmployeeStatus.ACTIVE)).thenReturn(employeeDbWrappers);
//         when(dbConvertor.fromEmployeeDbWrapper(employeeDbWrapper)).thenReturn(employee);

//         // Act
//         Collection<Employee> result = searchByEmployeeStatusService.searchEmployees(Search.builder().employeeStatus(EmployeeStatus.ACTIVE).build());

//         // Assert
//         assertFalse(result.isEmpty());
//         assertTrue(result.contains(employee));
//     }
// }