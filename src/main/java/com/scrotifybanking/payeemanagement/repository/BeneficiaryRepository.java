package com.scrotifybanking.payeemanagement.repository;


import com.scrotifybanking.payeemanagement.entity.Beneficiary;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
	
	List<Beneficiary> findByCustomerCustomerIdAndBeneficiaryId(Long customerId, Long beneficiaryId);


	Beneficiary findByBeneficiaryAccountNumber(Long beneficiaryAccountNo);

	List<Beneficiary> getAllByCustomerCustomerId(Long customerId);
	
    Optional<Integer> deleteByBeneficiaryIdAndCustomerCustomerId(Long beneficiaryId, Long customerId);
}
