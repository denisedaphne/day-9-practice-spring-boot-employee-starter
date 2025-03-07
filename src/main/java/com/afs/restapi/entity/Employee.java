package com.afs.restapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;
    private Long companyId;
    private static final Integer MIN_VALID_AGE = 18;

    public static final int MAX_VALID_AGE = 65;

    private Boolean active;

    public Employee() {
    }

    public Employee(Long id, String name, Integer age, String gender, Integer salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean hasInvalidAge() {
        return getAge() < MIN_VALID_AGE || getAge() > MAX_VALID_AGE;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isInactive() {
        return active != null && !active;
    }
    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}