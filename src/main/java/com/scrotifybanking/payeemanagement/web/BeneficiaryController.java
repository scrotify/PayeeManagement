package com.scrotifybanking.payeemanagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateResponseDto;
import com.scrotifybanking.payeemanagement.service.BeneficiaryService;

@RestController
@RequestMapping("customers/beneficiaries")
public class BeneficiaryController {
@Autowired
BeneficiaryService beneficiaryService;

@PutMapping("")
public BeneficiaryUpdateResponseDto updateBeneficiary(@RequestBody BeneficiaryUpdateRequestDto beneficiaryUpdateRequestDto)
		throws Exception {
	BeneficiaryUpdateResponseDto beneficiaryUpdateResponseDto=beneficiaryService.updateBeneficiary(beneficiaryUpdateRequestDto);
	beneficiaryUpdateResponseDto.setMessage("updated successfully");
	beneficiaryUpdateResponseDto.setStatusCode(201);
	return beneficiaryUpdateResponseDto;
}
}
