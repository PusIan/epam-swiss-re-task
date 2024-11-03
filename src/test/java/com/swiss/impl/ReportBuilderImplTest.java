package com.swiss.impl;

import com.swiss.ReportBuilder;
import com.swiss.model.Employee;
import com.swiss.model.OrganizationTree;
import com.swiss.model.report.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportBuilderImplTest {
    private ReportBuilder reportBuilder = new ReportBuilderImpl();

    @Test
    void buildReport_NoEmployeesWithLongReportLine_EmptyList() {
        Employee ceo = new Employee(1, "Test", "Test", 100, null);
        Employee employee = new Employee(2, "Test", "Test", 100, 1);
        OrganizationTree organizationTree = new OrganizationTree(ceo, Map.of(ceo, List.of(employee), employee, Collections.emptyList()));
        ReportConfiguration reportConfiguration = new ReportConfiguration(1, 0, 0);
        Report report = reportBuilder.buildReport(organizationTree, reportConfiguration);
        assertEquals(Collections.emptyList(), report.getEmployeeWithTooLongReportLineList());
    }

    @Test
    void buildReport_OneEmployeeWithLongReportLine_OneEmployeeWithLongReportLine() {
        Employee ceo = new Employee(1, "Test", "Test", 100, null);
        Employee employee = new Employee(2, "Test", "Test", 100, 1);
        OrganizationTree organizationTree = new OrganizationTree(ceo, Map.of(ceo, List.of(employee), employee, Collections.emptyList()));
        ReportConfiguration reportConfiguration = new ReportConfiguration(0, 0, 0);
        Report report = reportBuilder.buildReport(organizationTree, reportConfiguration);
        assertEquals(List.of(new EmployeeWithTooLongReportLine(employee, 1, 1)), report.getEmployeeWithTooLongReportLineList());
    }

    @Test
    void buildReport_OneManagerWithLowSalaryThenSubordinates_OneManagerWithLowSalary() {
        Employee ceo = new Employee(1, "Test", "Test", 110, null);
        Employee employee1 = new Employee(2, "Test", "Test", 100, 1);
        Employee employee2 = new Employee(3, "Test", "Test", 100, 1);
        OrganizationTree organizationTree = new OrganizationTree(ceo, Map.of(ceo, List.of(employee1, employee2),
                employee1, Collections.emptyList(),
                employee2, Collections.emptyList()));
        ReportConfiguration reportConfiguration = new ReportConfiguration(0, 1.11, 0);
        Report report = reportBuilder.buildReport(organizationTree, reportConfiguration);
        assertEquals(List.of(new ManagerEarnLess(ceo, 100, 1)), report.getManagerEarnLessList());
    }

    @Test
    void buildReport_NoManagerWithLowSalaryThenSubordinates_EmptyList() {
        Employee ceo = new Employee(1, "Test", "Test", 110, null);
        Employee employee1 = new Employee(2, "Test", "Test", 100, 1);
        Employee employee2 = new Employee(3, "Test", "Test", 100, 1);
        OrganizationTree organizationTree = new OrganizationTree(ceo, Map.of(ceo, List.of(employee1, employee2),
                employee1, Collections.emptyList(),
                employee2, Collections.emptyList()));
        ReportConfiguration reportConfiguration = new ReportConfiguration(0, 1.1, 0);
        Report report = reportBuilder.buildReport(organizationTree, reportConfiguration);
        assertEquals(Collections.emptyList(), report.getManagerEarnLessList());
    }

    @Test
    void buildReport_OneManagerWithHighSalaryThenSubordinates_OneManagerWithHighSalary() {
        Employee ceo = new Employee(1, "Test", "Test", 110, null);
        Employee employee1 = new Employee(2, "Test", "Test", 100, 1);
        Employee employee2 = new Employee(3, "Test", "Test", 100, 1);
        OrganizationTree organizationTree = new OrganizationTree(ceo, Map.of(ceo, List.of(employee1, employee2),
                employee1, Collections.emptyList(),
                employee2, Collections.emptyList()));
        ReportConfiguration reportConfiguration = new ReportConfiguration(0, 0, 1.09);
        Report report = reportBuilder.buildReport(organizationTree, reportConfiguration);
        assertEquals(List.of(new ManagerEarnMore(ceo, 100, 1)), report.getManagerEarnMoreList());
    }

    @Test
    void buildReport_NoManagerWithHighSalaryThenSubordinates_EmptyList() {
        Employee ceo = new Employee(1, "Test", "Test", 110, null);
        Employee employee1 = new Employee(2, "Test", "Test", 100, 1);
        Employee employee2 = new Employee(3, "Test", "Test", 100, 1);
        OrganizationTree organizationTree = new OrganizationTree(ceo, Map.of(ceo, List.of(employee1, employee2),
                employee1, Collections.emptyList(),
                employee2, Collections.emptyList()));
        ReportConfiguration reportConfiguration = new ReportConfiguration(0, 0, 1.1);
        Report report = reportBuilder.buildReport(organizationTree, reportConfiguration);
        assertEquals(Collections.emptyList(), report.getManagerEarnMoreList());
    }
}