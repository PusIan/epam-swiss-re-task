package com.swiss;

import com.swiss.model.report.Report;

import java.util.List;

public interface ReportSerializer {
    List<String> serialize(Report report);
}
