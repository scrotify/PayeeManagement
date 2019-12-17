package com.scrotifybanking.payeemanagement.repository;

import com.scrotifybanking.payeemanagement.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
	
    Optional<List<Bank>> findByBankIfscCodeContains(String bankIfscCode);

	Bank findByBankIfscCode(String ifscCode);

    Optional<Bank> findByBankIfscCode(String bankIfscCode);

}
