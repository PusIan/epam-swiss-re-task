package com.swiss.model.report;

import com.swiss.model.Employee;

import java.util.Objects;

public class EmployeeWithTooLongReportLine {
    private final Employee employee;
    private final Integer reportLine;
    private final Integer deviation;

    public EmployeeWithTooLongReportLine(Employee employee, Integer reportLine, Integer deviation) {
        this.employee = employee;
        this.reportLine = reportLine;
        this.deviation = deviation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Integer getReportLine() {
        return reportLine;
    }

    public Integer getDeviation() {
        return deviation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeWithTooLongReportLine that = (EmployeeWithTooLongReportLine) o;
        return Objects.equals(employee, that.employee) && Objects.equals(reportLine, that.reportLine)
                && Objects.equals(deviation, that.deviation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, reportLine, deviation);
    }

    @Override
    public String toString() {
        return "EmployeeWithTooLongReportLine{" +
                "employee=" + employee +
                ", reportLine=" + reportLine +
                ", deviation=" + deviation +
                '}';
    }
}
