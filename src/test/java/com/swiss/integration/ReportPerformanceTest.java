package com.swiss.integration;

import com.swiss.OrganizationDeserializer;
import com.swiss.ReportBuilder;
import com.swiss.ReportFacade;
import com.swiss.ReportSerializer;
import com.swiss.impl.CsvOrganizationDeserializer;
import com.swiss.impl.ReportBuilderImpl;
import com.swiss.impl.ReportFacadeImpl;
import com.swiss.impl.ReportSerializerImpl;
import com.swiss.model.report.ReportConfiguration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTimeout;

public class ReportPerformanceTest {
    private final String EXPECTED_HEADER = "Id,firstName,lastName,salary,managerId";
    private final Random random = new Random();

    @Test
    void buildReport_CsvFileWith1000Rows_TimeLessThen1Second() {
        OrganizationDeserializer organizationDeserializer = new CsvOrganizationDeserializer();
        ReportBuilder reportBuilder = new ReportBuilderImpl();
        ReportSerializer reportSerializer = new ReportSerializerImpl();
        ReportConfiguration reportConfiguration = new ReportConfiguration(5, 1.2, 1.5);
        ReportFacade reportFacade = new ReportFacadeImpl(organizationDeserializer, reportBuilder, reportSerializer);
        List<String> input = new ArrayList<>();
        input.add(EXPECTED_HEADER);
        Integer prev = null;
        for (int i = 0; i < 1000; i++) {
            input.add(String.format("%d,%s,%s,%s,%s", i, random.nextLong(), random.nextLong(), random.nextInt(), prev == null ? "" : prev));
            prev = i;
        }

        assertTimeout(Duration.ofSeconds(1), () -> reportFacade.prepareReport(input, reportConfiguration));
    }
}
