package com.ecobank.soole.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecobank.soole.models.Account;
import com.ecobank.soole.models.Report;
import com.ecobank.soole.repositories.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report save(Report report){
        if (report.getId() == null) {
            report.setCreatedAt(LocalDateTime.now());
        }

        report.setActivity(report.getActivity());
        report.setEmail(report.getEmail());

        report.setFullname(report.getFullname());
        
        report.setDepartment(report.getDepartment());

        return reportRepository.save(report);
    }

    public Page<Report> findReports(int page, int pageSize, String sortBy, String name, String department) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
        return reportRepository.findReports(name, department, pageable);
    }
    
}
