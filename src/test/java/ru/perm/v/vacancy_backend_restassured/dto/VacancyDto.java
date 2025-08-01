package ru.perm.v.vacancy_backend_restassured.dto;

import java.io.Serializable;
import java.util.Objects;

public class VacancyDto implements Serializable {
    Long n = -1L;
    String name;
    String comment;
    CompanyDto company;

    VacancyDto() {

    }

    public VacancyDto(Long n, String name) {
        this.n = n;
        this.name = name;
    }

    public Long getN() {
        return n;
    }

    public void setN(Long n) {
        this.n = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }
}
