package com.scrotifybanking.payeemanagement.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.scrotifybanking.payeemanagement.dto.ListBeneficiaryDto;
import com.scrotifybanking.payeemanagement.entity.Bank;
import com.scrotifybanking.payeemanagement.entity.Beneficiary;
import com.scrotifybanking.payeemanagement.entity.Customer;
import com.scrotifybanking.payeemanagement.repository.BeneficiaryRepository;
import com.scrotifybanking.payeemanagement.repository.CustomerRepository;
import com.scrotifybanking.payeemanagement.util.ScrotifyConstant;



@RunWith(MockitoJUnitRunner.Silent.class)
public class PayeeManagementViewServiceTest {
	
	@InjectMocks
	BeneficiaryServiceImpl beneficiaryServiceImpl;
	
	@Mock
	BeneficiaryRepository beneficiaryRepository;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Test
	public void testViewBeneficiariesPositive() {
		
		Bank bank = new Bank();
		bank.setBankName("indian");
		bank.setBankAddress("aaa");
		bank.setBankBranch("aaa");
		bank.setBankIfscCode("aaa");
		bank.setBankId(1L);
		bank.setBankPincode(122222L);
		
		Customer customer = new Customer();
		customer.setCustomerName("aaa");
		
		
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setAmountLimit(1222D);
		beneficiary.setBankIfscCode("aaa");
		beneficiary.setBankName("aa");
		beneficiary.setBeneficaryName("aaa");
		beneficiary.setBeneficiaryAccountNumber(122222L);
		beneficiary.setBeneficiaryId(1L);
		beneficiary.setCustomer(customer);
		beneficiary.setNickName("aaa");
		
        
        ListBeneficiaryDto list = new ListBeneficiaryDto();
        list.setAccountNo(123333L);
        list.setBankName("indian");
        list.setIfscCode("aaa");
        list.setLimit(1233D);
        list.setNickName("aaaa");
        list.setId(1L);
		
        List<ListBeneficiaryDto> list1 = new ArrayList<>();
        list1.add(list);
        
        List<Beneficiary> lists = new ArrayList<>();
        lists.add(beneficiary);
        
		Mockito.when(beneficiaryRepository.getAllByCustomerCustomerId(Mockito.anyLong())).thenReturn(lists);
		List<ListBeneficiaryDto> listBeneficiaryDto = beneficiaryServiceImpl.viewBeneficiaries(1L);
		Assert.assertNotNull(listBeneficiaryDto);
	}
	
	@Test
	public void testViewBeneficiariesNegative() {
		Customer customer = new Customer();
		ListBeneficiaryDto list = new ListBeneficiaryDto();
	    List<ListBeneficiaryDto> list1 = new ArrayList<>();
	    list1.add(list);
	    Beneficiary beneficiary = new Beneficiary();    
		beneficiary.setBankName("aaaa");
		beneficiary.setCustomer(customer);
        List<Beneficiary> lists = null;
		Mockito.when(beneficiaryRepository.getAllByCustomerCustomerId(Mockito.anyLong())).thenReturn(lists);
		assertEquals(ScrotifyConstant.NO_BENEFICIARY_ADDED, "No beneficiaries have added");
	}


}
