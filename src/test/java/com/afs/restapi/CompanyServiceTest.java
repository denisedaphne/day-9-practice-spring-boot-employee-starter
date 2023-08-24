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
        MockitoAnnotations.openMocks(this);
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
        assertThrows(CompanyNotFoundException.class, () -> companyService.findById(invalidCompanyId));
    }

    @Test
    void should_return_created_company_when_create_given_company_service_and_company() {
        // Given
        Company company = new Company(null, "OOCL");
        Company savedCompany = new Company(1L, "OOCL");
        when(companyJpaRepository.save(company)).thenReturn(savedCompany);

        // When
        Company createdCompany = companyService.create(company);

        // Then
        assertEquals(savedCompany.getId(), createdCompany.getId());
        assertEquals("OOCL", createdCompany.getName());
        verify(companyJpaRepository).save(company);
    }

    @Test
    void should_return_updated_company_when_update_given_company_service_and_company_id_and_company() {
        // Given
        Company company = new Company(1L, "OOCL");
        Company updatedCompanyInfo = new Company(null, "CMA CGM");
        when(companyJpaRepository.findById(company.getId())).thenReturn(Optional.of(company));
        when(companyJpaRepository.save(company)).thenReturn(company);

        // When
        Company updatedCompany = companyService.update(company.getId(), updatedCompanyInfo);

        // Then
        assertEquals(company.getId(), updatedCompany.getId());
        assertEquals("CMA CGM", updatedCompany.getName());
        verify(companyJpaRepository).save(company);
    }

    @Test
    void should_throw_exception_when_update_given_company_service_and_invalid_company_id_and_company() {
        // Given
        long invalidCompanyId = 999L;
        Company updatedCompanyInfo = new Company(null, "CMA CGM");
        when(companyJpaRepository.findById(invalidCompanyId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(CompanyNotFoundException.class, () -> companyService.update(invalidCompanyId, updatedCompanyInfo));
    }

    @Test
    void should_delete_company_when_delete_given_company_service_and_company_id() {
        // Given
        long companyId = 1L;

        // When
        companyService.delete(companyId);

        // Then
        verify(companyJpaRepository).deleteById(companyId);
    }
}
