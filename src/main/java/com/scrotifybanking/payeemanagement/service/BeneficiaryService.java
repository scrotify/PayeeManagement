package com.scrotifybanking.payeemanagement.service;

import com.scrotifybanking.payeemanagement.dto.*;
import com.scrotifybanking.payeemanagement.exception.CustomerNotFoundException;
import com.scrotifybanking.payeemanagement.exception.InvalidBankException;

import java.util.List;
import java.util.Optional;

public interface BeneficiaryService {

    BeneficiaryAddResponseDto addBeneficiary(Long customerId, BeneficiaryAddRequestDto beneficiaryAddRequestDto) throws InvalidBankException, CustomerNotFoundException;

    List<ListBeneficiaryDto> viewBeneficiaries(Long customerId);

    public BeneficiaryUpdateResponseDto updateBeneficiary(BeneficiaryUpdateRequestDto beneficiaryUpdateRequestDto)
            throws Exception;

    public Optional<Boolean> deleteBeneficiaryById(Long beneficiaryId, Long customerId);
}


