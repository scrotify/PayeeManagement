package com.scrotifybanking.payeemanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeneficiaryAddResponseDto {
	
	private Long beneficiaryId;
	private String message;
	private Integer statusCode;

}
