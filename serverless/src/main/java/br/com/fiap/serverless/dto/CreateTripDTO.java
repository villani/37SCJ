package br.com.fiap.serverless.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CreateTripDTO {
    
    private String country;
    private String city;
    private LocalDate date;
    private String reason;
    
}
