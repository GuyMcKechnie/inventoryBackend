package com.booklidio.booklidio_spring_backend.Analytics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.DateRange;
import com.google.analytics.data.v1beta.Dimension;
import com.google.analytics.data.v1beta.Metric;
import com.google.analytics.data.v1beta.Row;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;
import com.google.gson.Gson;

@Service
public class AnalyticsReportService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AnalyticsReportService.class);

    public String generateReport() {
        try (BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create()) {
            String timeSpan = "month";
            RunReportRequest request = RunReportRequest.newBuilder()
                    .setProperty("properties/" + 356932530)
                    .addDimensions(Dimension.newBuilder().setName(timeSpan))
                    .addMetrics(Metric.newBuilder().setName("activeUsers"))
                    .addDateRanges(DateRange.newBuilder().setStartDate("365daysAgo").setEndDate("today"))
                    .build();
            RunReportResponse response = analyticsData.runReport(request);
            if (response.getRowsCount() == 0) {
                logger.warn("No data found for the specified date range.");
                return "[]"; // Return an empty JSON array if no data is found
            } else {
                List<Map<String, String>> report = new ArrayList<>();
                for (Row row : response.getRowsList()) {
                    Map<String, String> rowRecord = new HashMap<>();
                    rowRecord.put(timeSpan, row.getDimensionValues(0).getValue());
                    rowRecord.put("visits", row.getMetricValues(0).getValue());
                    report.add(rowRecord);
                }
                report.sort(Comparator.comparing(m -> m.get(timeSpan)));
                Gson gson = new Gson();
                return gson.toJson(report);
            }
        } catch (IOException e) {
            logger.error("Error generating report: " + e.getMessage(), e);
            return "[]"; // Return an empty JSON array in case of an error
        }
    }
}
