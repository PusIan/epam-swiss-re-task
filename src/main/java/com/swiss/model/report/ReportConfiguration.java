package com.swiss.model.report;

public class ReportConfiguration {
    private final int maxAllowedReportLine;
    private final double minAllowedFraction;
    private final double maxAllowedFraction;

    public ReportConfiguration(int maxAllowedReportLine, double minAllowedFraction, double maxAllowedFraction) {
        this.maxAllowedReportLine = maxAllowedReportLine;
        this.minAllowedFraction = minAllowedFraction;
        this.maxAllowedFraction = maxAllowedFraction;
    }

    public int getMaxAllowedReportLine() {
        return maxAllowedReportLine;
    }

    public double getMinAllowedFraction() {
        return minAllowedFraction;
    }

    public double getMaxAllowedFraction() {
        return maxAllowedFraction;
    }
}
