package com.scrotifybanking.payeemanagement.repository;


import com.scrotifybanking.payeemanagement.entity.Beneficiary;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

import java.util.Optional;


public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
	
	Optional<Beneficiary> findByCustomerId(Long customerId);


	Beneficiary findByBeneficiaryAccountNumber(Long beneficiaryAccountNo);

	List<Beneficiary> findByCustomerCustomerId(Long customerId);
	
    Optional<Boolean> deleteByBeneficiaryIdAndCustomerCustomerId(Long beneficiaryId, Long customerId);
}
