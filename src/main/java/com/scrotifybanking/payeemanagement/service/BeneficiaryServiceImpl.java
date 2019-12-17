package com.scrotifybanking.payeemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddResponseDto;
import com.scrotifybanking.payeemanagement.entity.Bank;
import com.scrotifybanking.payeemanagement.entity.Beneficiary;
import com.scrotifybanking.payeemanagement.entity.Customer;
import com.scrotifybanking.payeemanagement.exception.CustomerNotFoundException;
import com.scrotifybanking.payeemanagement.exception.InvalidBankException;
import com.scrotifybanking.payeemanagement.repository.BankRepository;
import com.scrotifybanking.payeemanagement.repository.BeneficiaryRepository;
import com.scrotifybanking.payeemanagement.repository.CustomerRepository;
import com.scrotifybanking.payeemanagement.util.ScrotifyConstant;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	@Autowired
	BeneficiaryRepository beneficiaryRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BankRepository bankRepository;

	@Override
	public BeneficiaryAddResponseDto addBeneficiary(Long customerId, BeneficiaryAddRequestDto beneficiaryAddRequestDto)
			throws InvalidBankException, CustomerNotFoundException {
		Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
		Bank bank = bankRepository.findByBankIfscCode(beneficiaryAddRequestDto.getIfscCode());
		BeneficiaryAddResponseDto beneficiaryAddResponseDto = new BeneficiaryAddResponseDto();
		Beneficiary beneficiary = new Beneficiary();
		if (customer.isPresent()) {
			List<Beneficiary> beneficiaries = beneficiaryRepository.findByCustomerCustomerId(customerId);
			long beneficiaryCount = beneficiaries.size();
			if (beneficiaryCount <= 10) {
				beneficiary.setAmountLimit(beneficiaryAddRequestDto.getAmountLimit());
				beneficiary.setBeneficaryName(beneficiaryAddRequestDto.getBeneficaryName());
				beneficiary.setBeneficiaryAccountNumber(beneficiaryAddRequestDto.getBeneficiaryAccountNo());
				beneficiary.setNickName(beneficiaryAddRequestDto.getNickName());
				beneficiary.setCustomer(customer.get());
				if (bank.getBankIfscCode().equalsIgnoreCase(beneficiaryAddRequestDto.getIfscCode())
						&& bank.getBankName().equalsIgnoreCase(beneficiaryAddRequestDto.getBankName())) {
					beneficiary.setBankIfscCode(beneficiaryAddRequestDto.getIfscCode());
					beneficiary.setBankName(beneficiaryAddRequestDto.getBankName());
				} else {
					throw new InvalidBankException(ScrotifyConstant.INVALID_BANK);
				}
				beneficiaryRepository.save(beneficiary);
				beneficiaryAddResponseDto.setBeneficiaryId(beneficiary.getBeneficiaryId());
				beneficiaryAddResponseDto.setMessage(ScrotifyConstant.BENEFICIARY_MESSAGE);
				beneficiaryAddResponseDto.setStatusCode(ScrotifyConstant.CREATED_CODE);
			} else {
				beneficiaryAddResponseDto.setMessage(ScrotifyConstant.BENEFICIARY_EXCEED);
			}
			
		} else {
			throw new CustomerNotFoundException(ScrotifyConstant.CUSTOMER_ID_NOT_FOUND);
		}

		return beneficiaryAddResponseDto;
	}
}
