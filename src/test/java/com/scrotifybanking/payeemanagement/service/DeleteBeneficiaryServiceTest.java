package com.scrotifybanking.payeemanagement.service;

import com.scrotifybanking.payeemanagement.repository.BankRepository;
import com.scrotifybanking.payeemanagement.repository.BeneficiaryRepository;
import com.scrotifybanking.payeemanagement.service.BeneficiaryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class DeleteBeneficiaryServiceTest {

    @Mock
    BeneficiaryRepository beneficiaryRepository;

    @Mock
    BankRepository bankRepository;

    @InjectMocks
    private BeneficiaryServiceImpl beneficiaryService;

    @Test
    public void testDeleteBeneficiary() {
        Optional<Integer> deleteOptional = Optional.ofNullable(1);
        Mockito.when(beneficiaryRepository.deleteByBeneficiaryIdAndCustomerCustomerId(anyLong(), anyLong()))
                .thenReturn(deleteOptional);
        Optional<Boolean> res = beneficiaryService.deleteBeneficiaryById(10L, 100L);
        Assert.assertTrue(res.get());
    }

    @Test
    public void testDeleteBeneficiaryFalse() {
        Optional<Integer> deleteOptional = Optional.ofNullable(0);
        Mockito.when(beneficiaryRepository.deleteByBeneficiaryIdAndCustomerCustomerId(anyLong(), anyLong()))
                .thenReturn(deleteOptional);
        Optional<Boolean> res = beneficiaryService.deleteBeneficiaryById(10L, 100L);
        Assert.assertNotNull(res);
    }
}
