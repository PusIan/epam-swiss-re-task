package com.swiss.impl;

import com.swiss.ReportBuilder;
import com.swiss.constants.CoreConstants;
import com.swiss.model.Employee;
import com.swiss.model.OrganizationTree;
import com.swiss.model.report.*;

import java.util.*;

public class ReportBuilderImpl implements ReportBuilder {
    @Override
    public Report buildReport(OrganizationTree organizationTree, ReportConfiguration reportConfiguration) {
        List<EmployeeWithExtraData> employees = calcReportLineAndAverageSalaryOfSubordinates(organizationTree);
        return collectReport(employees, reportConfiguration);
    }

    private Report collectReport(List<EmployeeWithExtraData> employees, ReportConfiguration reportConfiguration) {
        List<EmployeeWithTooLongReportLine> employeeWithTooLongReportLines = new ArrayList<>();
        List<ManagerEarnLess> managerEarnLessList = new ArrayList<>();
        List<ManagerEarnMore> managerEarnMoreList = new ArrayList<>();
        for (EmployeeWithExtraData employeeWithExtraData : employees) {
            if (employeeWithExtraData.reportLineLength > reportConfiguration.getMaxAllowedReportLine()) {
                employeeWithTooLongReportLines.add(new EmployeeWithTooLongReportLine(employeeWithExtraData.employee,
                        employeeWithExtraData.reportLineLength(),
                        employeeWithExtraData.reportLineLength - reportConfiguration.getMaxAllowedReportLine()));
            }
            if (employeeWithExtraData.averageSalaryOfDirectSubordinates().isPresent()) {
                double averageOfSubordinates = employeeWithExtraData.averageSalaryOfDirectSubordinates().getAsDouble();
                double targetMin = reportConfiguration.getMinAllowedFraction() * averageOfSubordinates;
                double targetMax = reportConfiguration.getMaxAllowedFraction() * averageOfSubordinates;
                int employeeSalary = employeeWithExtraData.employee().getSalary();
                if (targetMin - employeeSalary > CoreConstants.EPSILON) {
                    managerEarnLessList.add(new ManagerEarnLess(employeeWithExtraData.employee,
                            averageOfSubordinates, targetMin - employeeSalary));
                }
                if (employeeSalary - targetMax > CoreConstants.EPSILON) {
                    managerEarnMoreList.add(new ManagerEarnMore(employeeWithExtraData.employee,
                            averageOfSubordinates, employeeSalary - targetMax));
                }
            }
        }
        return new Report(managerEarnLessList, managerEarnMoreList, employeeWithTooLongReportLines);
    }

    private List<EmployeeWithExtraData> calcReportLineAndAverageSalaryOfSubordinates(OrganizationTree organizationTree) {
        Deque<EmployeeWithExtraData> deque = new ArrayDeque<>();
        Employee ceo = organizationTree.getCeo();
        List<EmployeeWithExtraData> employees = new ArrayList<>();
        OptionalDouble averageSalaryOfDirectSubordinatesForManager = calcAverageSalaryOfDirectSubordinates(ceo, organizationTree);
        if (averageSalaryOfDirectSubordinatesForManager.isEmpty()) {
            return employees;
        }
        deque.add(new EmployeeWithExtraData(ceo, 0, averageSalaryOfDirectSubordinatesForManager));
        while (!deque.isEmpty()) {
            EmployeeWithExtraData employee = deque.remove();
            employees.add(employee);
            for (Employee subordinate : organizationTree.getTree().get(employee.employee)) {
                int subordinateReportLine = employee.reportLineLength() + 1;
                OptionalDouble subordinateAverageSalaryOfDirectSubordinates = calcAverageSalaryOfDirectSubordinates(subordinate, organizationTree);
                deque.add(new EmployeeWithExtraData(subordinate, subordinateReportLine, subordinateAverageSalaryOfDirectSubordinates));
            }
        }
        return employees;
    }

    private OptionalDouble calcAverageSalaryOfDirectSubordinates(Employee employee, OrganizationTree organizationTree) {
        return organizationTree.getTree().get(employee).stream().mapToInt(Employee::getSalary).average();
    }

    private record EmployeeWithExtraData(Employee employee, int reportLineLength,
                                         OptionalDouble averageSalaryOfDirectSubordinates) {
    }
}
