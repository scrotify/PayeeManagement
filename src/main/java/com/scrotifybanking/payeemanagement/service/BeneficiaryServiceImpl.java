package com.scrotifybanking.payeemanagement.service;


import com.scrotifybanking.payeemanagement.repository.BeneficiaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Beneficiary Service Impl
 *
 * @author Mahendran
 */
@Service
public class BeneficiaryServiceImpl implements BeneficiaryService{

    /**
     * The constant logger.
     */
    public static final Logger logger = LoggerFactory.getLogger(BeneficiaryServiceImpl.class);

    /**
     * The Beneficiary repository.
     */
    @Autowired
    BeneficiaryRepository beneficiaryRepository;
	 
	@Autowired
	BankRepository bankRepository;


    /**
     * Delete beneficiary by id optional.
     *
     * @param beneficiaryId the beneficiary id
     * @param customerId    the customer id
     * @return the optional
     */
    @Transactional
    public Optional<Boolean> deleteBeneficiaryById(Long beneficiaryId, Long customerId) {
        logger.info("Deleting the beneficiary by id :" + beneficiaryId +" for " + customerId);
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
