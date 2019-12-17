package com.scrotifybanking.payeemanagement.repository;


import com.scrotifybanking.payeemanagement.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

    Optional<Boolean> deleteByBeneficiaryIdAndCustomerCustomerId(Long beneficiaryId, Long customerId);
}
