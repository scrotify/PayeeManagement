package com.scrotifybanking.payeemanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BankDto {

    private Long bankId;

    private String bankBranch;

    private String bankName;

    private String bankIfscCode;

    private Long bankPincode;

    private Long bankAddress;

}
