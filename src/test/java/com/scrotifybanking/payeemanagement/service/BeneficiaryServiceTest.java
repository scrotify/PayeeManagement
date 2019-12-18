package com.scrotifybanking.payeemanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddResponseDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateResponseDto;
import com.scrotifybanking.payeemanagement.entity.Bank;
import com.scrotifybanking.payeemanagement.entity.Beneficiary;
import com.scrotifybanking.payeemanagement.entity.Customer;
import com.scrotifybanking.payeemanagement.exception.CustomerNotFoundException;
import com.scrotifybanking.payeemanagement.exception.InvalidBankException;
import com.scrotifybanking.payeemanagement.repository.BankRepository;
import com.scrotifybanking.payeemanagement.repository.BeneficiaryRepository;
import com.scrotifybanking.payeemanagement.repository.CustomerRepository;
import com.scrotifybanking.payeemanagement.util.ScrotifyConstant;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BeneficiaryServiceTest {

	@InjectMocks
	BeneficiaryServiceImpl beneficiaryServiceImpl;

	@Mock
	BeneficiaryRepository beneficiaryRepository;

	@Mock
	BankRepository bankRepository;

	@Mock
	CustomerRepository customerRepository;

	BeneficiaryUpdateRequestDto beneficiaryUpdateRequestDto = null;

	BeneficiaryUpdateResponseDto beneficiaryUpdateResponseDto = null;
	
	Beneficiary beneficiary = null;
	
	Customer customer = null;
	
	Bank bank = null;

	@Before
	public void setUp() {
		customer = new Customer();
		customer.setCustomerId(200L);

		bank = new Bank();
		bank.setBankIfscCode("sbin0009293");
		bank.setBankName("SBI");

		beneficiary = new Beneficiary();
		beneficiary.setAmountLimit(10000.0);
		beneficiary.setBankIfscCode("sbin0009293");
		beneficiary.setBankName("SBI");
		beneficiary.setBeneficaryName("Visha");
		beneficiary.setBeneficiaryAccountNumber(12345L);
		beneficiary.setBeneficiaryId(1L);
		beneficiary.setCustomer(customer);
		beneficiary.setNickName("Visha");

		beneficiaryUpdateRequestDto = new BeneficiaryUpdateRequestDto();
		beneficiaryUpdateRequestDto.setAccountNo(348266169L);
		beneficiaryUpdateRequestDto.setAmountLimit(20000.44);
		beneficiaryUpdateRequestDto.setBankIfscCode("sbin0009293");
		beneficiaryUpdateRequestDto.setBankName("sbi");
		beneficiaryUpdateRequestDto.setCustomerId(200L);
		
		beneficiaryUpdateResponseDto = new BeneficiaryUpdateResponseDto();
		beneficiaryUpdateResponseDto.setMessage(ScrotifyConstant.UPDATED);
		beneficiaryUpdateResponseDto.setStatusCode(201);
		
		
	}

	@Test(expected = Exception.class)
	public void testUpdateBeneficiaryForException() throws Exception {
		List<Beneficiary> beneficiaries = new ArrayList<>();
		beneficiaries.add(beneficiary);
		List<Bank> banks = new ArrayList<>();
		banks.add(bank);
		
		Mockito.when(beneficiaryRepository.findByBeneficiaryIdAndCustomerCustomerId(1L, 1L)).thenReturn(Optional.of(beneficiary));
		Mockito.when(bankRepository.findAllByBankName("SBI")).thenReturn(banks);
		BeneficiaryUpdateResponseDto response = beneficiaryServiceImpl.updateBeneficiary(beneficiaryUpdateRequestDto);
		
		assertEquals(ScrotifyConstant.NO_BENEFICIARY, response.getMessage());
		
	}
	

	@Test(expected = Exception.class)
	public void testUpdateBeneficiaryForInvalidIfsc() throws Exception {
		List<Beneficiary> beneficiaries = new ArrayList<>();
		beneficiaries.add(beneficiary);
		Bank banks = new Bank();
		banks.setBankIfscCode("sbi1000");
		banks.setBankName("SBI");
		List<Bank> bank = new ArrayList<>();
		bank.add(banks);
		
		Mockito.when(beneficiaryRepository.findByBeneficiaryIdAndCustomerCustomerId(beneficiaryUpdateRequestDto.getCustomerId(), beneficiaryUpdateRequestDto.getBeneficiaryId())).thenReturn(Optional.of(beneficiary));
		Mockito.when(bankRepository.findAllByBankName("sbi")).thenReturn(bank);
		BeneficiaryUpdateResponseDto response = beneficiaryServiceImpl.updateBeneficiary(beneficiaryUpdateRequestDto);
		
		assertEquals(ScrotifyConstant.INVALID_IFSC_CODE_MESSAGE, response.getMessage());
		
	}
	
	@Test
	public void testUpdateBeneficiary() throws Exception {
		List<Beneficiary> beneficiaries = new ArrayList<>();
		beneficiaries.add(beneficiary);
		List<Bank> banks = new ArrayList<>();
		banks.add(bank);
		
		Mockito.when(beneficiaryRepository.findByBeneficiaryIdAndCustomerCustomerId(beneficiaryUpdateRequestDto.getCustomerId(), beneficiaryUpdateRequestDto.getBeneficiaryId())).thenReturn(Optional.of(beneficiary));
		Mockito.when(bankRepository.findAllByBankName("sbi")).thenReturn(banks);
		BeneficiaryUpdateResponseDto response = beneficiaryServiceImpl.updateBeneficiary(beneficiaryUpdateRequestDto);
		
		assertEquals(ScrotifyConstant.UPDATED, response.getMessage());
		
	}
	
	@Test(expected = Exception.class)
	public void testUpdateBeneficiaryForInvalidBank() throws Exception {
		List<Beneficiary> beneficiaries = new ArrayList<>();
		beneficiaries.add(beneficiary);
		List<Bank> banks = new ArrayList<>();
		banks.add(bank);
		beneficiaryUpdateRequestDto.setBankName("icici");
		Mockito.when(beneficiaryRepository.findByBeneficiaryIdAndCustomerCustomerId(beneficiaryUpdateRequestDto.getCustomerId(), beneficiaryUpdateRequestDto.getBeneficiaryId())).thenReturn(Optional.of(beneficiary));
		Mockito.when(bankRepository.findAllByBankName("icici")).thenReturn(banks);
		BeneficiaryUpdateResponseDto response = beneficiaryServiceImpl.updateBeneficiary(beneficiaryUpdateRequestDto);
		
		assertEquals(ScrotifyConstant.INVALID_BANK_MESSAGE, response.getMessage());
		
	}

	@Test
	public void testAddBeneficiaryAccount() throws InvalidBankException, CustomerNotFoundException {
		Customer customer = new Customer();
		customer.setCustomerId(1L);

		Bank bank = new Bank();
		bank.setBankIfscCode("SBI1000");
		bank.setBankName("SBI");

		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setAmountLimit(10000.0);
		beneficiary.setBankIfscCode("SBI1000");
		beneficiary.setBankName("SBI");
		beneficiary.setBeneficaryName("Visha");
		beneficiary.setBeneficiaryAccountNumber(12345L);
		beneficiary.setBeneficiaryId(1L);
		beneficiary.setCustomer(customer);
		beneficiary.setNickName("Visha");

		BeneficiaryAddRequestDto beneficiaryAddRequestDto = new BeneficiaryAddRequestDto();
		beneficiaryAddRequestDto.setAmountLimit(beneficiary.getAmountLimit());
		beneficiaryAddRequestDto.setBankName(beneficiary.getBankName());
		beneficiaryAddRequestDto.setBeneficaryName(beneficiary.getBeneficaryName());
		beneficiaryAddRequestDto.setBeneficiaryAccountNo(beneficiary.getBeneficiaryAccountNumber());
		beneficiaryAddRequestDto.setIfscCode(beneficiary.getBankIfscCode());
		beneficiaryAddRequestDto.setNickName(beneficiary.getNickName());

		BeneficiaryAddResponseDto beneficiaryAddResponseDto = new BeneficiaryAddResponseDto();
		beneficiaryAddResponseDto.setBeneficiaryId(100L);
		beneficiaryAddResponseDto.setMessage(ScrotifyConstant.BENEFICIARY_MESSAGE);
		beneficiaryAddResponseDto.setStatusCode(ScrotifyConstant.CREATED_CODE);

		Mockito.when(customerRepository.findByCustomerId(1L)).thenReturn(Optional.of(customer));
		Mockito.when(bankRepository.findByBankIfscCode("SBI1000")).thenReturn(Optional.of(bank));
		Mockito.when(beneficiaryRepository.save(beneficiary)).thenReturn(beneficiary);
		BeneficiaryAddResponseDto result = beneficiaryServiceImpl.addBeneficiary(1L, beneficiaryAddRequestDto);

		assertEquals(ScrotifyConstant.BENEFICIARY_MESSAGE, result.getMessage());

	}

	@Test(expected = CustomerNotFoundException.class)
	public void testAddBeneficiaryAccountForCustomer() throws InvalidBankException, CustomerNotFoundException {
		Customer customer = new Customer();
		customer.setCustomerId(10L);

		Bank bank = new Bank();
		bank.setBankIfscCode("SBI1000");
		bank.setBankName("SBI");

		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setAmountLimit(10000.0);
		beneficiary.setBankIfscCode("SBI1000");
		beneficiary.setBankName("SBI");
		beneficiary.setBeneficaryName("Visha");
		beneficiary.setBeneficiaryAccountNumber(12345L);
		beneficiary.setBeneficiaryId(1L);
		beneficiary.setCustomer(customer);
		beneficiary.setNickName("Visha");

		BeneficiaryAddRequestDto beneficiaryAddRequestDto = new BeneficiaryAddRequestDto();
		beneficiaryAddRequestDto.setAmountLimit(beneficiary.getAmountLimit());
		beneficiaryAddRequestDto.setBankName(beneficiary.getBankName());
		beneficiaryAddRequestDto.setBeneficaryName(beneficiary.getBeneficaryName());
		beneficiaryAddRequestDto.setBeneficiaryAccountNo(beneficiary.getBeneficiaryAccountNumber());
		beneficiaryAddRequestDto.setIfscCode(beneficiary.getBankIfscCode());
		beneficiaryAddRequestDto.setNickName(beneficiary.getNickName());

		BeneficiaryAddResponseDto beneficiaryAddResponseDto = new BeneficiaryAddResponseDto();
		beneficiaryAddResponseDto.setBeneficiaryId(100L);
		beneficiaryAddResponseDto.setMessage(ScrotifyConstant.BENEFICIARY_MESSAGE);
		beneficiaryAddResponseDto.setStatusCode(ScrotifyConstant.CREATED_CODE);

		Mockito.when(customerRepository.findByCustomerId(1L)).thenReturn(Optional.of(customer));
		Mockito.when(bankRepository.findByBankIfscCode("SBI1000")).thenReturn(Optional.of(bank));
		Mockito.when(beneficiaryRepository.save(beneficiary)).thenReturn(beneficiary);
		BeneficiaryAddResponseDto result = beneficiaryServiceImpl.addBeneficiary(customer.getCustomerId(),
				beneficiaryAddRequestDto);

		assertEquals(ScrotifyConstant.CUSTOMER_ID_NOT_FOUND, result.getMessage());

	}
	
	@Test(expected = InvalidBankException.class)
	public void testAddBeneficiaryAccountForInvalidBank() throws InvalidBankException, CustomerNotFoundException {
		Customer customer = new Customer();
		customer.setCustomerId(1L);

		Bank bank = new Bank();
		bank.setBankIfscCode("SBI1000");
		bank.setBankName("SBI");

		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setAmountLimit(10000.0);
		beneficiary.setBankIfscCode("ICICI001");
		beneficiary.setBankName("ICICI");
		beneficiary.setBeneficaryName("Visha");
		beneficiary.setBeneficiaryAccountNumber(12345L);
		beneficiary.setBeneficiaryId(1L);
		beneficiary.setCustomer(customer);
		beneficiary.setNickName("Visha");

		BeneficiaryAddRequestDto beneficiaryAddRequestDto = new BeneficiaryAddRequestDto();
		beneficiaryAddRequestDto.setAmountLimit(beneficiary.getAmountLimit());
		beneficiaryAddRequestDto.setBankName(beneficiary.getBankName());
		beneficiaryAddRequestDto.setBeneficaryName(beneficiary.getBeneficaryName());
		beneficiaryAddRequestDto.setBeneficiaryAccountNo(beneficiary.getBeneficiaryAccountNumber());
		beneficiaryAddRequestDto.setIfscCode(beneficiary.getBankIfscCode());
		beneficiaryAddRequestDto.setNickName(beneficiary.getNickName());

		BeneficiaryAddResponseDto beneficiaryAddResponseDto = new BeneficiaryAddResponseDto();
		beneficiaryAddResponseDto.setBeneficiaryId(100L);
		beneficiaryAddResponseDto.setMessage(ScrotifyConstant.BENEFICIARY_MESSAGE);
		beneficiaryAddResponseDto.setStatusCode(ScrotifyConstant.CREATED_CODE);

		Mockito.when(customerRepository.findByCustomerId(1L)).thenReturn(Optional.of(customer));
		Mockito.when(bankRepository.findByBankIfscCode("ICICI001")).thenReturn(Optional.of(bank));
		Mockito.when(beneficiaryRepository.save(beneficiary)).thenReturn(beneficiary);
		BeneficiaryAddResponseDto result = beneficiaryServiceImpl.addBeneficiary(1L, beneficiaryAddRequestDto);

		assertEquals(ScrotifyConstant.INVALID_BANK, result.getMessage());

	}
	

}
