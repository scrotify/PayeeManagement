package com.scrotifybanking.payeemanagement.repository;


import com.scrotifybanking.payeemanagement.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

}
