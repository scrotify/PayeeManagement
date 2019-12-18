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
	Bank bank = null;

	@Before
	public void setUp() {
		Customer customer = new Customer();
		customer.setCustomerId(200L);
		beneficiaryUpdateRequestDto = new BeneficiaryUpdateRequestDto();
		beneficiaryUpdateRequestDto.setAccountNo(348266169L);
		beneficiaryUpdateRequestDto.setAmountLimit(20000.44);
		beneficiaryUpdateRequestDto.setBankIfscCode("sbin0009293");
		beneficiaryUpdateRequestDto.setBankName("sbi");
		beneficiaryUpdateRequestDto.setCustomerId(200L);
		beneficiaryUpdateResponseDto = new BeneficiaryUpdateResponseDto();
		beneficiaryUpdateResponseDto.setMessage("updated successfully");
		beneficiaryUpdateResponseDto.setStatusCode(201);
		beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryId(1L);
		beneficiary.setBankName("sbi");
		beneficiary.setAmountLimit(20000.44);
		beneficiary.setBeneficiaryAccountNumber(348266169L);
		beneficiary.setBankIfscCode("sbin0009293");
		beneficiary.setCustomer(customer);
		bank = new Bank();
		bank.setBankName("sbi");
		bank.setBankIfscCode("sbin0009293");
		bank.setBankAddress("aaa");
		bank.setBankBranch("aaa");
		bank.setBankPincode(12222L);
		
	}

//	@Test
//	public void testUpdateBeneficiary() throws Exception {
//		List<Beneficiary> customers = new ArrayList<>();
//		customers.add(beneficiary);
//		List<Bank> banks = new ArrayList<>();
//		banks.add(bank);
//		Mockito.when(beneficiaryRepository.findByCustomerCustomerIdAndBeneficiaryId(200L, 1L)).thenReturn(customers);
//		
//		Mockito.when(bankRepository.findAllByBankName("sbi")).thenReturn(banks);
//		BeneficiaryUpdateResponseDto response = beneficiaryServiceImpl.updateBeneficiary(beneficiaryUpdateRequestDto);
//		assertEquals(ScrotifyConstant.UPDATED, response.getMessage());
//	}

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

}
