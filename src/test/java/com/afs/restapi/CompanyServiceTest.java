package com.afs.restapi;

import com.afs.restapi.entity.Company;
import com.afs.restapi.exception.CompanyNotFoundException;
import com.afs.restapi.repository.CompanyJpaRepository;
import com.afs.restapi.repository.EmployeeJpaRepository;
import com.afs.restapi.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void should_return_company_when_get_by_id_given_company_service_and_company_id() {
        // Given
        Company company = new Company(1L, "OOCL");
        when(companyJpaRepository.findById(company.getId())).thenReturn(Optional.of(company));

        // When
        Company foundCompany = companyService.findById(company.getId());

        // Then
        assertEquals(company, foundCompany);
    }

    @Test
    void should_throw_exception_when_get_by_id_given_company_service_and_invalid_company_id() {
        // Given
        Long invalidCompanyId = 999L;
        when(companyJpaRepository.findById(invalidCompanyId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(CompanyNotFoundException.class, () -> {
            companyService.findById(invalidCompanyId);
        });
    }
}
