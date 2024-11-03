package com.swiss;

import com.swiss.impl.CsvOrganizationDeserializer;
import com.swiss.impl.ReportBuilderImpl;
import com.swiss.impl.ReportFacadeImpl;
import com.swiss.impl.ReportSerializerImpl;
import com.swiss.model.report.ReportConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("file1.csv");
        System.out.println(path.toAbsolutePath().toString());
        List<String> input = Files.readAllLines(path);
        OrganizationDeserializer organizationDeserializer = new CsvOrganizationDeserializer();
        ReportBuilder reportBuilder = new ReportBuilderImpl();
        ReportSerializer reportSerializer = new ReportSerializerImpl();
        ReportConfiguration reportConfiguration = new ReportConfiguration(5, 1.2, 1.5);
        ReportFacade reportFacade = new ReportFacadeImpl(organizationDeserializer, reportBuilder, reportSerializer);
        List<String> reportLines = reportFacade.prepareReport(input, reportConfiguration);
        reportLines.forEach(System.out::println);
    }
}
