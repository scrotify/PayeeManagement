package com.scrotifybanking.payeemanagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddResponseDto;
import com.scrotifybanking.payeemanagement.exception.CustomerNotFoundException;
import com.scrotifybanking.payeemanagement.exception.InvalidBankException;
import com.scrotifybanking.payeemanagement.service.BeneficiaryService;

@RestController
@RequestMapping("/beneficiaries")
@CrossOrigin
public class BeneficiaryController {

	@Autowired
	BeneficiaryService beneficiaryService;

	@PostMapping("/{customerId}")
	public BeneficiaryAddResponseDto addBeneficiaryAccount(@PathVariable Long customerId,
			@RequestBody BeneficiaryAddRequestDto beneficiaryAddRequestDto)
			throws InvalidBankException, CustomerNotFoundException {
		return beneficiaryService.addBeneficiary(customerId, beneficiaryAddRequestDto);
	}

}
