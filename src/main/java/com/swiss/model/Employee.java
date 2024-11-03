package com.swiss.model;

import java.util.Objects;

public class Employee {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final Integer salary;
    private final Integer managerId;

    public Employee(Integer id, String firstName, String lastName, Integer salary, Integer managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getManagerId() {
        return managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getId(), employee.getId()) && Objects.equals(getFirstName(), employee.getFirstName()) && Objects.equals(getLastName(), employee.getLastName()) && Objects.equals(getSalary(), employee.getSalary()) && Objects.equals(getManagerId(), employee.getManagerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getSalary(), getManagerId());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", managerId=" + managerId +
                '}';
    }
}
