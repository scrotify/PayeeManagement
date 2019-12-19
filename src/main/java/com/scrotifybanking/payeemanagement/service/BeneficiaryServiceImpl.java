package com.scrotifybanking.payeemanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddResponseDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateResponseDto;
import com.scrotifybanking.payeemanagement.dto.ListBeneficiaryDto;
import com.scrotifybanking.payeemanagement.entity.Bank;
import com.scrotifybanking.payeemanagement.entity.Beneficiary;
import com.scrotifybanking.payeemanagement.entity.Customer;
import com.scrotifybanking.payeemanagement.exception.CommonException;
import com.scrotifybanking.payeemanagement.exception.CustomException;
import com.scrotifybanking.payeemanagement.exception.CustomerNotFoundException;
import com.scrotifybanking.payeemanagement.exception.InvalidBankException;
import com.scrotifybanking.payeemanagement.repository.BankRepository;
import com.scrotifybanking.payeemanagement.repository.BeneficiaryRepository;
import com.scrotifybanking.payeemanagement.repository.CustomerRepository;
import com.scrotifybanking.payeemanagement.util.ScrotifyConstant;

/**
 * The type Beneficiary Service
 * 
 * @author
 *
 */
@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	/**
	 * The constant logger.
	 */
	public static final Logger logger = LoggerFactory.getLogger(BeneficiaryServiceImpl.class);

	@Autowired
	BeneficiaryRepository beneficiaryRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BankRepository bankRepository;

	/**
	 * Ths method is used to view the beneficiary details
	 */
	@Override
	public List<ListBeneficiaryDto> viewBeneficiaries(Long customerId) {
		logger.info("Entering into view beneficiary service method");
		List<ListBeneficiaryDto> beneficiaryDtoList = new ArrayList<>();
		List<Beneficiary> listBeneficiary = beneficiaryRepository.getAllByCustomerCustomerId(customerId);
		listBeneficiary.stream().forEach(beneficiary -> {
			ListBeneficiaryDto listBeneficiaryDto = new ListBeneficiaryDto();
			listBeneficiaryDto.setAccountNo(beneficiary.getBeneficiaryAccountNumber());
			listBeneficiaryDto.setBankName(beneficiary.getBankName());
			listBeneficiaryDto.setIfscCode(beneficiary.getBankIfscCode());
			listBeneficiaryDto.setLimit(beneficiary.getAmountLimit());
			listBeneficiaryDto.setNickName(beneficiary.getNickName());
			listBeneficiaryDto.setId(beneficiary.getBeneficiaryId());
			listBeneficiaryDto.setName(beneficiary.getBeneficaryName());
			beneficiaryDtoList.add(listBeneficiaryDto);
		});
		if (beneficiaryDtoList.isEmpty()) {
			throw new CommonException(ScrotifyConstant.NO_BENEFICIARY_ADDED);
		}

		return beneficiaryDtoList;
	}

	/**
	 * This method is used to add beneficiary account
	 */
	@Override
	public BeneficiaryAddResponseDto addBeneficiary(Long customerId, BeneficiaryAddRequestDto beneficiaryAddRequestDto)
			throws InvalidBankException, CustomerNotFoundException {
		logger.info("Entering into add beneficiary account service method");
		Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
		Optional<Bank> bank = bankRepository.findByBankIfscCode(beneficiaryAddRequestDto.getIfscCode());
		BeneficiaryAddResponseDto beneficiaryAddResponseDto = new BeneficiaryAddResponseDto();
		Beneficiary beneficiary = new Beneficiary();
		if (customer.isPresent()) {
			List<Beneficiary> beneficiaries = beneficiaryRepository.getAllByCustomerCustomerId(customerId);
			long beneficiaryCount = beneficiaries.size();
			if (beneficiaryCount <= 10) {
				beneficiary.setAmountLimit(beneficiaryAddRequestDto.getAmountLimit());
				beneficiary.setBeneficaryName(beneficiaryAddRequestDto.getBeneficaryName());
				beneficiary.setBeneficiaryAccountNumber(beneficiaryAddRequestDto.getBeneficiaryAccountNo());
				beneficiary.setNickName(beneficiaryAddRequestDto.getNickName());
				beneficiary.setCustomer(customer.get());
				if (bank.isPresent()) {
					if (bank.get().getBankIfscCode().equalsIgnoreCase(beneficiaryAddRequestDto.getIfscCode())
							&& bank.get().getBankName().equalsIgnoreCase(beneficiaryAddRequestDto.getBankName())) {
						beneficiary.setBankIfscCode(beneficiaryAddRequestDto.getIfscCode());
						beneficiary.setBankName(beneficiaryAddRequestDto.getBankName());
					} else {
						throw new InvalidBankException(ScrotifyConstant.INVALID_BANK);
					}
				} else {
					beneficiaryAddResponseDto.setMessage(ScrotifyConstant.NO_BANK);
					beneficiaryAddResponseDto.setStatusCode(ScrotifyConstant.NOT_FOUND_CODE);
				}
				beneficiaryRepository.save(beneficiary);
				beneficiaryAddResponseDto.setBeneficiaryId(beneficiary.getBeneficiaryId());
				beneficiaryAddResponseDto.setMessage(ScrotifyConstant.BENEFICIARY_MESSAGE);
				beneficiaryAddResponseDto.setStatusCode(ScrotifyConstant.CREATED_CODE);
			} else {
				beneficiaryAddResponseDto.setMessage(ScrotifyConstant.BENEFICIARY_EXCEED);
				beneficiaryAddResponseDto.setStatusCode(ScrotifyConstant.NOT_FOUND_CODE);
			}

		} else {
			throw new CustomerNotFoundException(ScrotifyConstant.CUSTOMER_ID_NOT_FOUND);
		}

		return beneficiaryAddResponseDto;
	}

	/**
	 * Delete beneficiary by id optional.
	 *
	 * @param beneficiaryId the beneficiary id
	 * @param customerId    the customer id
	 * @return the optional
	 */
	@Transactional
	@Override
	public Optional<Boolean> deleteBeneficiaryById(Long beneficiaryId, Long customerId) {
		logger.info("Deleting the beneficiary by id :");
		Optional<Integer> deleteOptional = beneficiaryRepository
				.deleteByBeneficiaryIdAndCustomerCustomerId(beneficiaryId, customerId);
		logger.info("Deleted the beneficiary ");
		if (deleteOptional.isPresent()) {
			return Optional.ofNullable(true);
		}else {
		return Optional.ofNullable(false);
		}
	}

	/**
	 * This method is used to update beneficiary account details
	 */
	@Override
	public BeneficiaryUpdateResponseDto updateBeneficiary(BeneficiaryUpdateRequestDto beneficiaryUpdateRequestDto) {
		logger.info("Entering into update beneficiary account service method");
		BeneficiaryUpdateResponseDto beneficiaryUpdateResponseDto = new BeneficiaryUpdateResponseDto();
		Optional<Beneficiary> optionalUser = beneficiaryRepository.findByBeneficiaryIdAndCustomerCustomerId(
				beneficiaryUpdateRequestDto.getCustomerId(), beneficiaryUpdateRequestDto.getBeneficiaryId());
		if (optionalUser.isPresent()) {
			Beneficiary beneficiary = optionalUser.get();
			if (beneficiaryUpdateRequestDto.getAccountNo() != null) {
				beneficiary.setBeneficiaryAccountNumber(beneficiaryUpdateRequestDto.getAccountNo());
				beneficiaryRepository.save(beneficiary);

			}else {
				beneficiaryUpdateResponseDto.setMessage(ScrotifyConstant.EMPTY_ACCOUNT_NO);
			}
			if (beneficiaryUpdateRequestDto.getAmountLimit() != null) {
				beneficiary.setAmountLimit(beneficiaryUpdateRequestDto.getAmountLimit());
				beneficiaryRepository.save(beneficiary);

			}else {
				beneficiaryUpdateResponseDto.setMessage(ScrotifyConstant.EMPTY_AMOUNT_LIMIT);
			}
			List<Bank> bankDeatils = bankRepository.findAllByBankName(beneficiaryUpdateRequestDto.getBankName());
			for (Bank banks : bankDeatils) {
				if (beneficiaryUpdateRequestDto.getBankName() != null) {
					if (banks.getBankName().equalsIgnoreCase(beneficiaryUpdateRequestDto.getBankName())) {
						beneficiary.setBankName(beneficiaryUpdateRequestDto.getBankName());
						beneficiaryRepository.save(beneficiary);

					} else {
						throw new CustomException(ScrotifyConstant.INVALID_BANK_MESSAGE);
					}

				}else {
					beneficiaryUpdateResponseDto.setMessage(ScrotifyConstant.EMPTY_BANK);
				}
				if (beneficiaryUpdateRequestDto.getBankIfscCode() != null) {
					if (banks.getBankIfscCode().equalsIgnoreCase(beneficiaryUpdateRequestDto.getBankIfscCode())) {
						beneficiary.setBankIfscCode(beneficiaryUpdateRequestDto.getBankIfscCode());
						beneficiaryRepository.save(beneficiary);
						beneficiaryUpdateResponseDto.setMessage(ScrotifyConstant.UPDATED);
					} else {
						throw new CustomException(ScrotifyConstant.INVALID_IFSC_CODE_MESSAGE);
					}

				}else {
					beneficiaryUpdateResponseDto.setMessage(ScrotifyConstant.EMPTY_IFSC_CODE);
				}
			}
		} else {
			throw new CustomException(ScrotifyConstant.NO_BENEFICIARY);
		}
		return beneficiaryUpdateResponseDto;
	}

}
