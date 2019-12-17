package com.scrotifybanking.payeemanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddResponseDto;
import com.scrotifybanking.payeemanagement.dto.ListBeneficiaryDto;
import com.scrotifybanking.payeemanagement.entity.Bank;
import com.scrotifybanking.payeemanagement.entity.Beneficiary;
import com.scrotifybanking.payeemanagement.entity.Customer;
import com.scrotifybanking.payeemanagement.exception.CommonException;
import com.scrotifybanking.payeemanagement.exception.CustomerNotFoundException;
import com.scrotifybanking.payeemanagement.exception.InvalidBankException;
import com.scrotifybanking.payeemanagement.repository.BankRepository;
import com.scrotifybanking.payeemanagement.repository.BeneficiaryRepository;
import com.scrotifybanking.payeemanagement.repository.CustomerRepository;
import com.scrotifybanking.payeemanagement.util.ScrotifyConstant;

import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateResponseDto;
import com.scrotifybanking.payeemanagement.entity.Bank;
import com.scrotifybanking.payeemanagement.entity.Beneficiary;
import com.scrotifybanking.payeemanagement.repository.BankRepository;
import com.scrotifybanking.payeemanagement.repository.BeneficiaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


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
	  

	@Override
	public List<ListBeneficiaryDto> viewBeneficiaries(Long customerId) {
		List<ListBeneficiaryDto> beneficiaryDtoList = new ArrayList<>();
		List<Beneficiary> listBeneficiary = beneficiaryRepository.findByCustomerCustomerId(customerId);
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

	@Override
	public BeneficiaryAddResponseDto addBeneficiary(Long customerId, BeneficiaryAddRequestDto beneficiaryAddRequestDto)
			throws InvalidBankException, CustomerNotFoundException {
		Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
		Optional<Bank> bank = bankRepository.findByBankIfscCode(beneficiaryAddRequestDto.getIfscCode());
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
				if (bank.get().getBankIfscCode().equalsIgnoreCase(beneficiaryAddRequestDto.getIfscCode())
						&& bank.get().getBankName().equalsIgnoreCase(beneficiaryAddRequestDto.getBankName())) {
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
        logger.info("Deleting the beneficiary by id :" + beneficiaryId + " for " + customerId);
        Optional<Boolean> deleteOptional = beneficiaryRepository.deleteByBeneficiaryIdAndCustomerCustomerId(beneficiaryId, customerId);
        logger.info("Deleted the beneficiary ");
        if (deleteOptional.isPresent()) {
            return Optional.ofNullable(deleteOptional.get());
        }
        return Optional.ofNullable(false);
    }


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
            if (beneficiaryUpdateRequestDto.getAmountLimit() != null) {
                beneficiary.setAmountLimit(beneficiaryUpdateRequestDto.getAmountLimit());
                beneficiaryRepository.save(beneficiary);

            }
            Optional<Bank> bankDeatils = bankRepository.findByBankName(beneficiaryUpdateRequestDto.getBankName());
            if (beneficiaryUpdateRequestDto.getBankName() != null) {
                if (bankDeatils.get().getBankName().equalsIgnoreCase(beneficiaryUpdateRequestDto.getBankName())) {
                    beneficiary.setBankName(beneficiaryUpdateRequestDto.getBankName());
                    beneficiaryRepository.save(beneficiary);

                } else {
                    throw new Exception("invalid bank name/bank name doesn't exit");
                }

            }
            if (beneficiaryUpdateRequestDto.getBankIfscCode() != null) {
                if (bankDeatils.get().getBankIfscCode().equalsIgnoreCase(beneficiaryUpdateRequestDto.getBankIfscCode())) {
                    beneficiary.setBankIfscCode(beneficiaryUpdateRequestDto.getBankIfscCode());
                    beneficiaryRepository.save(beneficiary);
                    beneficiaryUpdateResponseDto.setMessage("updated successfully");
                } else {
                    throw new Exception("invalid ifsc code/ifsc code doesn't exit ");
                }

            }
        } else {
            throw new Exception("customer doesn't have beneficiary details");
        }
        return beneficiaryUpdateResponseDto;
    }

}
