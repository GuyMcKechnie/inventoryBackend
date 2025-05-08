package com.booklidio.booklidio_spring_backend.Analytics;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.DateRange;
import com.google.analytics.data.v1beta.Dimension;
import com.google.analytics.data.v1beta.Metric;
import com.google.analytics.data.v1beta.Row;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

@Service
public class AnalyticsReportService {

    public String generateReport() throws IOException {
        try (BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create()) {
            RunReportRequest request = RunReportRequest.newBuilder()
                    .setProperty("properties/" + 356932530)
                    .addDimensions(Dimension.newBuilder().setName("month"))
                    .addMetrics(Metric.newBuilder().setName("activeUsers"))
                    .addDateRanges(DateRange.newBuilder().setStartDate("365daysAgo").setEndDate("today"))
                    .build();
            RunReportResponse response = analyticsData.runReport(request);
            List<Map<String, String>> report = new ArrayList<>();
            for (Row row : response.getRowsList()) {
                Map<String, String> record = new HashMap<>();
                record.put("month", row.getDimensionValues(0).getValue());
                record.put("visits", row.getMetricValues(0).getValue());
                report.add(record);
            }

            report.sort(Comparator.comparing(m -> m.get("month")));

            Gson gson = new Gson();
            return gson.toJson(report);
        }
    }
}
