package com.swiss;

import com.swiss.model.OrganizationTree;
import com.swiss.model.report.Report;
import com.swiss.model.report.ReportConfiguration;

public interface ReportBuilder {
    Report buildReport(OrganizationTree organizationTree, ReportConfiguration reportConfiguration);
}
