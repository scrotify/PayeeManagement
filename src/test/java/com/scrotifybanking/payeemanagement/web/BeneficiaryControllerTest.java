package com.scrotifybanking.payeemanagement.web;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyLong;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.scrotifybanking.payeemanagement.dto.ApiResponse;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddResponseDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateResponseDto;
import com.scrotifybanking.payeemanagement.dto.DeleteBeneficiaryDto;
import com.scrotifybanking.payeemanagement.entity.Bank;
import com.scrotifybanking.payeemanagement.entity.Beneficiary;
import com.scrotifybanking.payeemanagement.entity.Customer;
import com.scrotifybanking.payeemanagement.exception.CustomerNotFoundException;
import com.scrotifybanking.payeemanagement.exception.InvalidBankException;
import com.scrotifybanking.payeemanagement.service.BeneficiaryServiceImpl;
import com.scrotifybanking.payeemanagement.util.ScrotifyConstant;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class BeneficiaryControllerTest {

	@InjectMocks
	BeneficiaryController beneficiaryController;

	@Mock
	BeneficiaryServiceImpl beneficiaryServiceImpl;

	BeneficiaryUpdateRequestDto beneficiaryUpdateRequestDto = null;

	BeneficiaryUpdateResponseDto beneficiaryUpdateResponseDto = null;

	@Before
	public void setUp() {
		beneficiaryUpdateRequestDto = new BeneficiaryUpdateRequestDto();
		beneficiaryUpdateRequestDto.setAccountNo(348266169L);
		beneficiaryUpdateRequestDto.setAmountLimit(20000.44);
		beneficiaryUpdateRequestDto.setBankIfscCode("sbin0009293");
		beneficiaryUpdateRequestDto.setBankName("sbi");
		beneficiaryUpdateRequestDto.setCustomerId(200L);
		beneficiaryUpdateResponseDto = new BeneficiaryUpdateResponseDto();
		beneficiaryUpdateResponseDto.setMessage("updated successfully");
		beneficiaryUpdateResponseDto.setStatusCode(201);
	}

	@Test
	public void testUpdateBeneficiary() throws Exception {
		Mockito.when(beneficiaryServiceImpl.updateBeneficiary(beneficiaryUpdateRequestDto))
				.thenReturn(beneficiaryUpdateResponseDto);
		BeneficiaryUpdateResponseDto response = beneficiaryController.updateBeneficiary(beneficiaryUpdateRequestDto);
		Assert.assertNotNull(response);
	}
	
	@Test
	public void testAddBeneficiary() throws InvalidBankException, CustomerNotFoundException {
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
		
		Mockito.when(beneficiaryServiceImpl.addBeneficiary(1L, beneficiaryAddRequestDto)).thenReturn(beneficiaryAddResponseDto);
		BeneficiaryAddResponseDto result = beneficiaryController.addBeneficiaryAccount(1L, beneficiaryAddRequestDto);
		
		

		
		assertEquals(ScrotifyConstant.BENEFICIARY_MESSAGE, result.getMessage());
		
	}
	
	 @Test
	    public void testDeleteBeneficiary() {
	        Optional<Boolean> res = Optional.ofNullable(true);

	        Mockito.when(
	                beneficiaryServiceImpl.deleteBeneficiaryById(anyLong(), anyLong())
	        ).thenReturn(res);
	        DeleteBeneficiaryDto deleteBeneficiaryDto = new DeleteBeneficiaryDto();
	        deleteBeneficiaryDto.setBeneficiaryId(10L);
	        deleteBeneficiaryDto.setCustomerId(100L);
	        ResponseEntity<ApiResponse> response = beneficiaryController.deleteBeneficiary(10L, 10L);
	        Assert.assertNotNull(response);
	    }

	    @Test
	    public void testUpdateBeneficiaryFor() throws Exception {
	        BeneficiaryUpdateResponseDto beneficiaryUpdateResponseDto = new BeneficiaryUpdateResponseDto();
	        beneficiaryUpdateResponseDto.setStatusCode(200);
	        beneficiaryUpdateResponseDto.setMessage("Success");
	        Mockito.when(
	                beneficiaryServiceImpl.updateBeneficiary(any())
	        ).thenReturn(beneficiaryUpdateResponseDto);
	        BeneficiaryUpdateRequestDto updateRequestDto = new BeneficiaryUpdateRequestDto();
	        updateRequestDto.setAccountNo(111L);
	        updateRequestDto.setAmountLimit(10.0);
	        BeneficiaryUpdateResponseDto response = beneficiaryController.updateBeneficiary(updateRequestDto);
	        Assert.assertNotNull(response);
	    }


}
