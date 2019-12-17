package com.scrotifybanking.payeemanagement.repository;


import com.scrotifybanking.payeemanagement.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

    Optional<Beneficiary> findByCustomerId(Long customerId);


    Beneficiary findByBeneficiaryAccountNumber(Long beneficiaryAccountNo);

    List<Beneficiary> findByCustomerCustomerId(Long customerId);

    Optional<Boolean> deleteByBeneficiaryIdAndCustomerCustomerId(Long beneficiaryId, Long customerId);
}
