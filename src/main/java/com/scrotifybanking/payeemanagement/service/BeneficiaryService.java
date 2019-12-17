package com.scrotifybanking.payeemanagement.service;

import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateResponseDto;

import java.util.Optional;

public interface BeneficiaryService {
	public BeneficiaryUpdateResponseDto updateBeneficiary(BeneficiaryUpdateRequestDto beneficiaryUpdateRequestDto)
			throws Exception;

    public Optional<Boolean> deleteBeneficiaryById(Long beneficiaryId, Long customerId);
}

