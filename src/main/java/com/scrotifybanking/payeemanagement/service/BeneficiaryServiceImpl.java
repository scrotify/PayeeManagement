package com.scrotifybanking.payeemanagement.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateResponseDto;
import com.scrotifybanking.payeemanagement.entity.Bank;
import com.scrotifybanking.payeemanagement.entity.Beneficiary;
import com.scrotifybanking.payeemanagement.repository.BankRepository;
import com.scrotifybanking.payeemanagement.repository.BeneficiaryRepository;
@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
	@Autowired
	BeneficiaryRepository beneficiaryRepository;
	@Autowired
	BankRepository bankRepository;

	@Override
	public BeneficiaryUpdateResponseDto updateBeneficiary(BeneficiaryUpdateRequestDto beneficiaryUpdateRequestDto)
			throws Exception {
		
		BeneficiaryUpdateResponseDto beneficiaryUpdateResponseDto = new BeneficiaryUpdateResponseDto();
		Optional<Beneficiary> optionalUser = beneficiaryRepository
				.findByCustomerId(beneficiaryUpdateRequestDto.getCustomerId());
		if (optionalUser.isPresent()) {
			Beneficiary beneficiary = optionalUser.get();
			if (beneficiaryUpdateRequestDto.getAccountNo() != null) {
				beneficiary.setBeneficiaryAccountNumber(beneficiaryUpdateRequestDto.getAccountNo());
				beneficiaryRepository.save(beneficiary);
				
			}
			if (beneficiaryUpdateRequestDto.getAmountLimit()!= null) {
				beneficiary.setAmountLimit(beneficiaryUpdateRequestDto.getAmountLimit());
				beneficiaryRepository.save(beneficiary);
			
			}
			Optional<Bank> bankDeatils=bankRepository.findByBankName(beneficiaryUpdateRequestDto.getBankName());
			if(beneficiaryUpdateRequestDto.getBankName()!=null) {
				if(bankDeatils.get().getBankName().equalsIgnoreCase(beneficiaryUpdateRequestDto.getBankName())){
					beneficiary.setBankName(beneficiaryUpdateRequestDto.getBankName());
					beneficiaryRepository.save(beneficiary);
				
				}else {
					throw new Exception("invalid bank name/bank name doesn't exit");
				}
				
			}if(beneficiaryUpdateRequestDto.getBankIfscCode()!=null) {
				if(bankDeatils.get().getBankIfscCode().equalsIgnoreCase(beneficiaryUpdateRequestDto.getBankIfscCode())) {
					beneficiary.setBankIfscCode(beneficiaryUpdateRequestDto.getBankIfscCode());
					beneficiaryRepository.save(beneficiary);
					beneficiaryUpdateResponseDto.setMessage("updated successfully");
				}else {
					throw new Exception("invalid ifsc code/ifsc code doesn't exit ");
				}
				
			}
		} else {
			throw new Exception("customer doesn't have beneficiary details");
		}
		return beneficiaryUpdateResponseDto;
	}

}