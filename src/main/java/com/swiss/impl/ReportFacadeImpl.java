package com.swiss.impl;

import com.swiss.OrganizationDeserializer;
import com.swiss.ReportBuilder;
import com.swiss.ReportFacade;
import com.swiss.ReportSerializer;
import com.swiss.model.OrganizationTree;
import com.swiss.model.report.Report;
import com.swiss.model.report.ReportConfiguration;

import java.util.List;

public class ReportFacadeImpl implements ReportFacade {
    private final OrganizationDeserializer organizationDeserializer;
    private final ReportBuilder reportBuilder;
    private final ReportSerializer reportSerializer;

    public ReportFacadeImpl(OrganizationDeserializer organizationDeserializer,
                            ReportBuilder reportBuilder, ReportSerializer reportSerializer) {
        this.organizationDeserializer = organizationDeserializer;
        this.reportBuilder = reportBuilder;
        this.reportSerializer = reportSerializer;
    }

    @Override
    public List<String> prepareReport(List<String> input, ReportConfiguration reportConfiguration) {
        OrganizationTree organizationTree = organizationDeserializer.deserialize(input);
        Report report = reportBuilder.buildReport(organizationTree, reportConfiguration);
        return reportSerializer.serialize(report);
    }
}
