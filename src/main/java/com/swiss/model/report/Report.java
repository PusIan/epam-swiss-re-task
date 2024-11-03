package com.swiss.model.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Report {
    List<ManagerEarnLess> managerEarnLessList = new ArrayList<>();
    List<ManagerEarnMore> managerEarnMoreList = new ArrayList<>();
    List<EmployeeWithTooLongReportLine> employeeWithTooLongReportLineList = new ArrayList<>();

    public Report(List<ManagerEarnLess> managerEarnLessList, List<ManagerEarnMore> managerEarnMoreList, List<EmployeeWithTooLongReportLine> employeeWithTooLongReportLineList) {
        this.managerEarnLessList = managerEarnLessList;
        this.managerEarnMoreList = managerEarnMoreList;
        this.employeeWithTooLongReportLineList = employeeWithTooLongReportLineList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(getManagerEarnLessList(), report.getManagerEarnLessList()) && Objects.equals(getManagerEarnMoreList(), report.getManagerEarnMoreList()) && Objects.equals(getEmployeeWithTooLongReportLineList(), report.getEmployeeWithTooLongReportLineList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getManagerEarnLessList(), getManagerEarnMoreList(), getEmployeeWithTooLongReportLineList());
    }

    public List<ManagerEarnLess> getManagerEarnLessList() {
        return managerEarnLessList;
    }

    public List<ManagerEarnMore> getManagerEarnMoreList() {
        return managerEarnMoreList;
    }

    public List<EmployeeWithTooLongReportLine> getEmployeeWithTooLongReportLineList() {
        return employeeWithTooLongReportLineList;
    }
}
