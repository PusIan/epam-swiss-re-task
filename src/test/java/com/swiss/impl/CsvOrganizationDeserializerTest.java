package com.swiss.impl;

import com.swiss.exceptions.*;
import com.swiss.model.Employee;
import com.swiss.model.OrganizationTree;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CsvOrganizationDeserializerTest {
    private final String EXPECTED_HEADER = "Id,firstName,lastName,salary,managerId";
    private CsvOrganizationDeserializer deserializer = new CsvOrganizationDeserializer();

    @Test
    void deserialize_WhenNoHeaderFound_ThrowsNoHeaderFoundException() {
        List<String> reportInputCsv = new ArrayList<>();
        reportInputCsv.add(EXPECTED_HEADER + ",extrafield");
        assertThrows(NoHeaderFoundException.class, () -> deserializer.deserialize(reportInputCsv));
    }

    @Test
    void deserialize_WhenRecordHasWrongLen_ThrowsWrongNumberOfFieldsInCsvException() {
        List<String> reportInputCsv = new ArrayList<>();
        reportInputCsv.add(EXPECTED_HEADER);
        reportInputCsv.add("1,Ivan,Ivan,100,100,1");
        assertThrows(WrongNumberOfFieldsInCsvException.class, () -> deserializer.deserialize(reportInputCsv));
    }

    @Test
    void deserialize_WhenMultipleCeo_ThrowsMultipleCeoAreNotAllowedException() {
        List<String> reportInputCsv = new ArrayList<>();
        reportInputCsv.add(EXPECTED_HEADER);
        reportInputCsv.add("1,Ivan,Ivan,100,");
        reportInputCsv.add("2,Ivan,Ivan,100,");
        assertThrows(MultipleCeoAreNotAllowedException.class, () -> deserializer.deserialize(reportInputCsv));
    }

    @Test
    void deserialize_WhenDuplicateId_ThrowsDuplicateEmployeeByIdException() {
        List<String> reportInputCsv = new ArrayList<>();
        reportInputCsv.add(EXPECTED_HEADER);
        reportInputCsv.add("1,Ivan,Ivan,100,");
        reportInputCsv.add("2,Ivan,Ivan,100,1");
        reportInputCsv.add("2,Ivan,Ivan,100,1");
        assertThrows(DuplicateEmployeeByIdException.class, () -> deserializer.deserialize(reportInputCsv));
    }

    @Test
    void deserialize_WhenNoCeoProvided_ThrowsCeoNotFoundException() {
        List<String> reportInputCsv = new ArrayList<>();
        reportInputCsv.add(EXPECTED_HEADER);
        reportInputCsv.add("1,Ivan,Ivan,100,2");
        reportInputCsv.add("2,Ivan,Ivan,100,1");
        assertThrows(CeoNotFoundException.class, () -> deserializer.deserialize(reportInputCsv));
    }

    @Test
    void deserialize_WhenManagerIdIsWrong_ThrowsEmployeeNotFoundException() {
        List<String> reportInputCsv = new ArrayList<>();
        reportInputCsv.add(EXPECTED_HEADER);
        reportInputCsv.add("1,Ivan,Ivan,100,");
        reportInputCsv.add("2,Ivan,Ivan,100,100");
        assertThrows(EmployeeNotFoundException.class, () -> deserializer.deserialize(reportInputCsv));
    }

    @Test
    void deserialize_OneCeo_CorrectResult() {
        List<String> reportInputCsv = new ArrayList<>();
        reportInputCsv.add(EXPECTED_HEADER);
        reportInputCsv.add("1,Ivan,Ivan,100,");
        Employee ceo = new Employee(1, "Ivan", "Ivan", 100, null);
        OrganizationTree expectedOrgTree = new OrganizationTree(
                ceo,
                Map.of(ceo, Collections.emptyList()));

        OrganizationTree organizationTree = deserializer.deserialize(reportInputCsv);
        assertEquals(expectedOrgTree, organizationTree);
    }

    @Test
    void deserialize_CeoWithTwoSubordinates_CorrectResult() {
        List<String> reportInputCsv = new ArrayList<>();
        reportInputCsv.add(EXPECTED_HEADER);
        reportInputCsv.add("1,Ivan,Ivan,100,");
        reportInputCsv.add("2,Ivan,Ivan,100,1");
        reportInputCsv.add("3,Ivan,Ivan,100,1");
        Employee ceo = new Employee(1, "Ivan", "Ivan", 100, null);
        Employee employee1 = new Employee(2, "Ivan", "Ivan", 100, 1);
        Employee employee2 = new Employee(3, "Ivan", "Ivan", 100, 1);
        OrganizationTree expectedOrgTree = new OrganizationTree(
                ceo,
                Map.of(ceo, List.of(employee1, employee2),
                        employee1, Collections.emptyList(),
                        employee2, Collections.emptyList()));

        OrganizationTree organizationTree = deserializer.deserialize(reportInputCsv);

        assertEquals(expectedOrgTree, organizationTree);
    }
}