package com.scrotifybanking.payeemanagement.service;

import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddResponseDto;
import com.scrotifybanking.payeemanagement.exception.CustomerNotFoundException;
import com.scrotifybanking.payeemanagement.exception.InvalidBankException;

public interface BeneficiaryService {
	
	BeneficiaryAddResponseDto addBeneficiary(Long customerId, BeneficiaryAddRequestDto beneficiaryAddRequestDto) throws InvalidBankException, CustomerNotFoundException;

}
