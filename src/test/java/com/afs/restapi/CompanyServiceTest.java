package com.afs.restapi;

import com.afs.restapi.entity.Company;
import com.afs.restapi.repository.CompanyJpaRepository;
import com.afs.restapi.repository.EmployeeJpaRepository;
import com.afs.restapi.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class CompanyServiceTest {

    private CompanyService companyService;
    @Mock
    private CompanyJpaRepository companyJpaRepository;
    @Mock
    private EmployeeJpaRepository employeeJpaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        companyService = new CompanyService(companyJpaRepository, employeeJpaRepository);
    }

    @Test
    void should_return_all_companies_when_get_all_given_company_service() {
        // Given
        Company company = new Company(1L, "OOCL");
        List<Company> companies = List.of(company);
        when(companyJpaRepository.findAll()).thenReturn(companies);

        // When
        List<Company> allCompanies = companyService.findAll();

        // Then
        assertEquals(companies, allCompanies);
    }
}
