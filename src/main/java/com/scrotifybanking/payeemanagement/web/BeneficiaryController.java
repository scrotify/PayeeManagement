package com.scrotifybanking.payeemanagement.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddResponseDto;
import com.scrotifybanking.payeemanagement.dto.ListBeneficiaryDto;
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
	
	@GetMapping("/{customerId}")
	public ResponseEntity<List<ListBeneficiaryDto>> viewBeneficiaries(@PathVariable("customerId") Long customerId) {
	List<ListBeneficiaryDto> listBeneficiaryDto = beneficiaryService.viewBeneficiaries(customerId);
	return new ResponseEntity<>(listBeneficiaryDto, HttpStatus.OK);
	}


}
