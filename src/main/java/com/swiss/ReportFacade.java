package com.swiss;

import com.swiss.model.report.ReportConfiguration;

import java.util.List;

public interface ReportFacade {
    List<String> prepareReport(List<String> input, ReportConfiguration reportConfiguration);
}
