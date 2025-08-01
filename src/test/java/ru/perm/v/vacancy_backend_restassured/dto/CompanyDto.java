package ru.perm.v.vacancy_backend_restassured.dto;

import java.io.Serializable;
import java.util.Objects;

public class CompanyDto implements Serializable {
    Long n = -1L;
    String name;
    CompanyDto() {

    }

    public CompanyDto(Long n, String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyDto)) return false;
        CompanyDto that = (CompanyDto) o;
        return Objects.equals(n, that.n) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, name);
    }

    @Override
    public String toString() {
        return "CompanyDto{" +
                "n=" + n +
                ", name='" + name + '\'' +
                '}';
    }
}
