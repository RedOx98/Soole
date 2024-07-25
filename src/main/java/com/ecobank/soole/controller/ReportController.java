package com.ecobank.soole.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecobank.soole.models.Account;
import com.ecobank.soole.models.Report;
import com.ecobank.soole.payload.reports.ReportViewDTO;
import com.ecobank.soole.services.ReportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/audit")
@Slf4j
@Tag(name = "Report Controller", description = "Controller for Report management")

public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/reportspaginate")
    @ApiResponse(responseCode = "200", description = "List of users pagination")
    @ApiResponse(responseCode = "401", description = "Token missing")
    @ApiResponse(responseCode = "403", description = "Token error")
    @Operation(summary = "List users in paginated format")
    @SecurityRequirement(name = "soole-demo-api")
    public ResponseEntity<List<ReportViewDTO>> allUsers(
            @RequestParam(required = false, name = "sort_by", defaultValue = "createdAt") String sort_by,
            @RequestParam(required = false, name = "per_page", defaultValue = "2") String per_page,
            @RequestParam(required = false, name = "page", defaultValue = "1") String page,
            @RequestParam(required = false, name = "name", defaultValue = "") String name,
            @RequestParam(required = false, name = "department", defaultValue = "") String department
            ) {
        Page<Report> reportsOnPage = reportService.findReports(Integer.parseInt(page) - 1,
                Integer.parseInt(per_page), sort_by, name, department);
        List<Report> reportList = reportsOnPage.getContent();
        int totalPages = reportsOnPage.getTotalPages();
        List<Integer> pages = new ArrayList<>();
        if (totalPages > 0) {
            pages = IntStream.rangeClosed(0, totalPages - 1).boxed().collect(Collectors.toList());
        }

        if (pages != null) {
            for (int link : pages) {
                // String active = "";
                if (link == reportsOnPage.getNumber()) {
                    // active = "active";
                }
                // Convert Account to AccountViewDTO
                List<ReportViewDTO> reports = reportList.stream().map(report -> new ReportViewDTO(
                    report.getId(),
                    report.getFullname(),
                    report.getEmail(),
                    report.getActivity(),
                    report.getDepartment(),
                    report.getDate()
                        )).collect(Collectors.toList());
                System.out.println(reports);
                return ResponseEntity.ok(reports);
            }
        }
        System.out.println(reportsOnPage);
        return null;
    }
}
