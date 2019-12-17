package com.scrotifybanking.payeemanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BeneficiaryUpdateRequestDto {
    private Long customerId;
    private Long accountNo;
    private String bankName;
    private String bankIfscCode;
    private Double amountLimit;

}
