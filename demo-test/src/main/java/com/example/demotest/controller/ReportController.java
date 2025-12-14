package com.example.demotest.controller;

import com.example.demotest.config.ApiResponse;
import com.example.demotest.dto.UserReport;
import com.example.demotest.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    @GetMapping
    public ApiResponse<List<UserReport>> getActivityReport() {
        return ApiResponse.success(reportService.getUserReport(), "Successfuly get activity report", HttpStatus.OK);
    }

}
