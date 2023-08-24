package com.afs.restapi;

import com.afs.restapi.entity.Employee;
import com.afs.restapi.repository.EmployeeJpaRepository;
import com.afs.restapi.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @Mock
    private EmployeeJpaRepository employeeJpaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeService(employeeJpaRepository);
    }

    @Test
    void should_return_all_employees_when_get_employees_given_employee_service() {
        // Given
        Employee employee = new Employee(1L, "Lucy", 20, "Female", 3000);
        List<Employee> employees = List.of(employee);
        when(employeeJpaRepository.findAll()).thenReturn(employees);

        // When
        List<Employee> allEmployees = employeeService.findAll();

        // Then
        assertEquals(allEmployees.get(0).getId(), employee.getId());
        assertEquals(allEmployees.get(0).getName(), employee.getName());
        assertEquals(allEmployees.get(0).getAge(), employee.getAge());
        assertEquals(allEmployees.get(0).getGender(), employee.getGender());
        assertEquals(allEmployees.get(0).getSalary(), employee.getSalary());
    }

    @Test
    void should_return_the_employee_when_get_employee_given_employee_service_and_an_employee_id() {
        // Given
        Employee employee = new Employee(1L, "Lucy", 20, "Female", 3000);
        when(employeeJpaRepository.findById(employee.getId())).thenReturn(java.util.Optional.of(employee));

        // When
        Employee foundEmployee = employeeService.findById(employee.getId());

        // Then
        assertEquals(employee, foundEmployee);
    }
}