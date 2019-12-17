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
public class BeneficiaryServiceImpl {

    /**
     * The constant logger.
     */
    public static final Logger logger = LoggerFactory.getLogger(BeneficiaryServiceImpl.class);

    /**
     * The Beneficiary repository.
     */
    @Autowired
    BeneficiaryRepository beneficiaryRepository;


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
}
