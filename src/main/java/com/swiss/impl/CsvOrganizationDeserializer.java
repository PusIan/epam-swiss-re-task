package com.swiss.impl;

import com.swiss.OrganizationDeserializer;
import com.swiss.exceptions.*;
import com.swiss.model.Employee;
import com.swiss.model.OrganizationTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvOrganizationDeserializer implements OrganizationDeserializer {
    private final int RECORD_FIELD_LEN_CEO = 4;
    private final int RECORD_FIELD_LEN_NOT_CEO = 5;
    private final String EXPECTED_HEADER = "Id,firstName,lastName,salary,managerId";

    @Override
    public OrganizationTree deserialize(List<String> input) {
        Map<Employee, List<Employee>> tree = new HashMap<>();
        Map<Integer, Employee> employees = new HashMap<>();
        Employee ceo = null;
        boolean isHeaderSkippedAlready = false;
        if (!input.get(0).equals(EXPECTED_HEADER)) {
            throw new NoHeaderFoundException();
        }
        for (String line : input) {
            if (!isHeaderSkippedAlready) {
                isHeaderSkippedAlready = true;
                continue;
            }
            String[] parts = line.split(",");
            Employee employee = null;
            if (parts.length == RECORD_FIELD_LEN_CEO) {
                if (ceo != null) {
                    throw new MultipleCeoAreNotAllowedException();
                }
                employee = new Employee(Integer.valueOf(parts[0]), parts[1], parts[2],
                        Integer.valueOf(parts[3]), null);
                ceo = employee;
            } else if (parts.length == RECORD_FIELD_LEN_NOT_CEO) {
                employee = new Employee(Integer.valueOf(parts[0]), parts[1], parts[2],
                        Integer.valueOf(parts[3]), Integer.valueOf(parts[4]));
            } else {
                throw new WrongNumberOfFieldsInCsvException(parts.length);
            }

            if (employees.containsKey(employee.getId())) {
                throw new DuplicateEmployeeByIdException(employee.getId());
            }
            tree.put(employee, new ArrayList<>());
            employees.put(employee.getId(), employee);
        }
        if (ceo == null) {
            throw new CeoNotFoundException();
        }
        for (Employee employee : tree.keySet()) {
            if (employee.getManagerId() != null) {
                if (!employees.containsKey(employee.getManagerId())) {
                    throw new EmployeeNotFoundException(employee.getManagerId());
                }
                Employee manager = employees.get(employee.getManagerId());
                tree.get(manager).add(employee);
            }
        }
        return new OrganizationTree(ceo, tree);
    }
}
