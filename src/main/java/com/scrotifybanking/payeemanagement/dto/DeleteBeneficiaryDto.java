package com.scrotifybanking.payeemanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DeleteBeneficiaryDto {

    private Long customerId;
    private Long beneficiaryId;

}
