package com.ecobank.soole.payload.reports;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportViewDTO {
    
    private Long id;

    private String fullname;

    private String email;

    private String activity;

    private String department;

    private LocalDateTime date;
}
