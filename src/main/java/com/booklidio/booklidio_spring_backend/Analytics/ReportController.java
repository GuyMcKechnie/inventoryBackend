package com.booklidio.booklidio_spring_backend.Analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ReportController.class);
    @Autowired
    private AnalyticsReportService analyticsReportService;

    @GetMapping("/traffic")
    public ResponseEntity<String> getTrafficReport() {
        try {
            return new ResponseEntity<>(analyticsReportService.generateReport(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error generating report: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
