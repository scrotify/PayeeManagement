package com.scrotifybanking.payeemanagement.web;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.scrotifybanking.payeemanagement.dto.BankDto;
import com.scrotifybanking.payeemanagement.entity.Bank;
import com.scrotifybanking.payeemanagement.service.BankService;

@RunWith(MockitoJUnitRunner.class)
public class BankControllerTest {
	
	@InjectMocks
	BankController bankController;
	
	@Mock
	BankService bankService;
	
	@Test
	public void getByIfscCode() {
		Bank bank = new Bank();
		bank.setBankAddress("no:20");
		bank.setBankBranch("SBI");
		bank.setBankId(1L);
		bank.setBankIfscCode("SBI1000");
		bank.setBankName("SBI");
		bank.setBankPincode(768342L);
		
		BankDto bankDto = new BankDto();
		bankDto.setBankAddress(bank.getBankAddress());
		bankDto.setBankBranch(bank.getBankBranch());
		bankDto.setBankId(bank.getBankId());
		bankDto.setBankIfscCode(bank.getBankIfscCode());
		bankDto.setBankName(bank.getBankName());
		bankDto.setBankPincode(bank.getBankPincode());
		
		Mockito.when(bankService.getBankByIfscCode(bankDto.getBankIfscCode())).thenReturn(bankDto);
		ResponseEntity<BankDto> result = bankController.getBankDetailsByIfscCode(bankDto.getBankIfscCode());
		assertNotNull(result);
	}

}
