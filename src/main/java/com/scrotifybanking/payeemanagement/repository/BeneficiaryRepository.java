package com.scrotifybanking.payeemanagement.repository;


import com.scrotifybanking.payeemanagement.entity.Beneficiary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

	Beneficiary findByBeneficiaryAccountNumber(Long beneficiaryAccountNo);

	List<Beneficiary> findByCustomerCustomerId(Long customerId);

}
