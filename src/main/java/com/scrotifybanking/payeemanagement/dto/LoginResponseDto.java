package com.scrotifybanking.payeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * The type Login response dto.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    private Long customerId;

    private String customerName;

    private String customerEmail;

    private String customerPassword;

    private LocalDate customerDob;

    private Double customerSalary;

    private Integer customerAge;

    private String customerRole;

    private Long customerMobileNo;

    private String customerCity;

    private Integer statusCode;
    private String message;
}
