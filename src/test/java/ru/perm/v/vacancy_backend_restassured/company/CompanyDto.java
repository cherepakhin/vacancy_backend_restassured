package ru.perm.v.vacancy_backend_restassured.company;

public class CompanyDto {
    Long n = -1L;
    String name;
    CompanyDto() {

    }

    public CompanyDto(Long n, String name) {
        this.n = n;
        this.name = name;
    }
}
