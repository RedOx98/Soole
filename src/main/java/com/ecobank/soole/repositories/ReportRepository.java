package com.ecobank.soole.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecobank.soole.models.Report;

public interface ReportRepository extends JpaRepository<Report, Long>{

  @Query("SELECT r FROM Report r WHERE " +
           "(:name IS NULL OR r.fullname LIKE CONCAT('%', :name, '%')) " + 
           "AND (:department IS NULL OR r.department LIKE CONCAT('%', :department, '%'))")
    Page<Report> findReports(
        @Param("name") String name,
        @Param("department") String department,
        Pageable pageable
    );
    
}
