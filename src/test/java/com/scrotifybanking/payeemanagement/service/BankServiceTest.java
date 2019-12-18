package com.scrotifybanking.payeemanagement.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.scrotifybanking.payeemanagement.dto.BankDto;
import com.scrotifybanking.payeemanagement.entity.Bank;
import com.scrotifybanking.payeemanagement.exception.CustomException;
import com.scrotifybanking.payeemanagement.repository.BankRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BankServiceTest {
	
	@InjectMocks
	BankServiceImpl bankServiceImpl;
	
	@Mock
	BankRepository bankRepository;
	
	@Test
	public void testGetByIfsc() {
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
		
		Mockito.when(bankRepository.findByBankIfscCode("SBI1000")).thenReturn(Optional.of(bank));
		BankDto result = bankServiceImpl.getBankByIfscCode("SBI1000");
		assertNotNull(result);
	}
	
	@Test(expected = CustomException.class)
	public void testGetByIfscNegative() {
		Bank bank = new Bank();
		
		BankDto bankDto = new BankDto();
		bankDto.setBankAddress(bank.getBankAddress());
		bankDto.setBankBranch(bank.getBankBranch());
		bankDto.setBankId(bank.getBankId());
		bankDto.setBankIfscCode(bank.getBankIfscCode());
		bankDto.setBankName(bank.getBankName());
		bankDto.setBankPincode(bank.getBankPincode());
		
		Mockito.when(bankRepository.findByBankIfscCode("SBI10001")).thenReturn(Optional.of(bank));
		BankDto result = bankServiceImpl.getBankByIfscCode("SBI1000");
		assertNull(result);
	}

}
