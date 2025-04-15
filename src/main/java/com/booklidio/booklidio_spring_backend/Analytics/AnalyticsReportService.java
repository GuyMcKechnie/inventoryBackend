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

@Service
public class AnalyticsReportService {

    public String generateReport() throws IOException {
        try (BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -5);
            String oneYearAgo = simpleDateFormat.format(calendar.getTime());
            RunReportRequest request = RunReportRequest.newBuilder()
                    .setProperty("properties/" + 356932530)
                    .addDimensions(Dimension.newBuilder().setName("month"))
                    .addMetrics(Metric.newBuilder().setName("activeUsers"))
                    .addDateRanges(DateRange.newBuilder().setStartDate("2021-01-01").setEndDate("today"))
                    .build();
            RunReportResponse response = analyticsData.runReport(request);
            StringBuilder json = new StringBuilder("[");
            for (Row row : response.getRowsList()) {
                json.append("{\"month\":\"").append(row.getDimensionValues(0).getValue()).append("\",\"visits\":")
                        .append(row.getMetricValues(0).getValue()).append("},");
            }
            if (json.length() > 1) {
                json.deleteCharAt(json.length() - 1);
            }
            json.append("]");
            return json.toString();
        }
    }
}
