package com.swiss.impl;

import com.swiss.ReportSerializer;
import com.swiss.model.report.EmployeeWithTooLongReportLine;
import com.swiss.model.report.ManagerEarnLess;
import com.swiss.model.report.ManagerEarnMore;
import com.swiss.model.report.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportSerializerImpl implements ReportSerializer {
    @Override
    public List<String> serialize(Report report) {
        List<String> lines = new ArrayList<>();
        lines.add("The list of managers who earn more then");
        lines.add("Id,FirstName,LastName,Salary,AverageSalaryOfSubordinates,Deviation");
        for (ManagerEarnMore managerEarnMore : report.getManagerEarnMoreList()) {
            lines.add(String.format("%d,%s,%s,%d,%.2f,%.2f",
                    managerEarnMore.getEmployee().getId(),
                    managerEarnMore.getEmployee().getFirstName(),
                    managerEarnMore.getEmployee().getLastName(),
                    managerEarnMore.getEmployee().getSalary(),
                    managerEarnMore.getAverageOfSubordinates(),
                    managerEarnMore.getDeviation()));
        }
        lines.add("The list of managers who earn less then");
        lines.add("Id,FirstName,LastName,Salary,AverageSalaryOfSubordinates,Deviation");
        for (ManagerEarnLess managerEarnLess : report.getManagerEarnLessList()) {
            lines.add(String.format("%d,%s,%s,%d,%.2f,%.2f",
                    managerEarnLess.getEmployee().getId(),
                    managerEarnLess.getEmployee().getFirstName(),
                    managerEarnLess.getEmployee().getLastName(),
                    managerEarnLess.getEmployee().getSalary(),
                    managerEarnLess.getAverageOfSubordinates(),
                    managerEarnLess.getDeviation()));
        }

        lines.add("The list of employees with too long report line");
        lines.add("Id,FirstName,LastName,Salary,ReportLine,Deviation");
        for (EmployeeWithTooLongReportLine employee : report.getEmployeeWithTooLongReportLineList()) {
            lines.add(String.format("%d,%s,%s,%d,%d,%d",
                    employee.getEmployee().getId(),
                    employee.getEmployee().getFirstName(),
                    employee.getEmployee().getLastName(),
                    employee.getEmployee().getSalary(),
                    employee.getReportLine(),
                    employee.getDeviation()));
        }
        return lines;
    }
}
