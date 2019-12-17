package com.scrotifybanking.payeemanagement.service;

import java.util.List;

import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddResponseDto;
import com.scrotifybanking.payeemanagement.dto.ListBeneficiaryDto;
import com.scrotifybanking.payeemanagement.exception.CustomerNotFoundException;
import com.scrotifybanking.payeemanagement.exception.InvalidBankException;

public interface BeneficiaryService {
	
	BeneficiaryAddResponseDto addBeneficiary(Long customerId, BeneficiaryAddRequestDto beneficiaryAddRequestDto) throws InvalidBankException, CustomerNotFoundException;

	List<ListBeneficiaryDto> viewBeneficiaries(Long customerId);

}
