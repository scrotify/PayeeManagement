package com.scrotifybanking.payeemanagement.web;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.scrotifybanking.payeemanagement.dto.ListBeneficiaryDto;
import com.scrotifybanking.payeemanagement.entity.Customer;
import com.scrotifybanking.payeemanagement.service.BeneficiaryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PayeeManagementViewControllerTest {

	@InjectMocks
	BeneficiaryController beneficiaryController;

	@Mock
	BeneficiaryServiceImpl beneficiaryServiceImpl;

	@Test
	public void testBeneficiary() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerId(1L);

		ListBeneficiaryDto list = new ListBeneficiaryDto();
		list.setAccountNo(123333L);
		list.setBankName("indian");
		list.setIfscCode("aaa");
		list.setLimit(1233D);
		list.setNickName("aaaa");
		list.setId(1L);

		List<ListBeneficiaryDto> list1 = new ArrayList<>();
		list1.add(list);

		Mockito.when(beneficiaryServiceImpl.viewBeneficiaries(1L)).thenReturn(list1);

		ResponseEntity<List<ListBeneficiaryDto>> accountNosDtos = beneficiaryController.viewBeneficiaries(1L);
		Assert.assertNotNull(accountNosDtos);
		Assert.assertEquals(200, accountNosDtos.getStatusCode().value());
	}

}
