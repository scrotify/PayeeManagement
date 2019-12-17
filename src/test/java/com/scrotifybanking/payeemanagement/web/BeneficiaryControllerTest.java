package com.scrotifybanking.payeemanagement.web;

import com.scrotifybanking.payeemanagement.dto.ApiResponse;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateResponseDto;
import com.scrotifybanking.payeemanagement.dto.DeleteBeneficiaryDto;
import com.scrotifybanking.payeemanagement.service.BeneficiaryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class BeneficiaryControllerTest {

    @InjectMocks
    BeneficiaryController beneficiaryController;

    @Mock
    BeneficiaryServiceImpl beneficiaryService;

    @Test
    public void testDeleteBeneficiary() {
        Optional<Boolean> res = Optional.ofNullable(true);

        Mockito.when(
                beneficiaryService.deleteBeneficiaryById(anyLong(), anyLong())
        ).thenReturn(res);
        DeleteBeneficiaryDto deleteBeneficiaryDto = new DeleteBeneficiaryDto();
        deleteBeneficiaryDto.setBeneficiaryId(10L);
        deleteBeneficiaryDto.setCustomerId(100L);
        ResponseEntity<ApiResponse> response = beneficiaryController.deleteBeneficiary(deleteBeneficiaryDto);
        Assert.assertNotNull(response);
    }

    @Test
    public void testUpdateBeneficiary() throws Exception {
        BeneficiaryUpdateResponseDto beneficiaryUpdateResponseDto = new BeneficiaryUpdateResponseDto();
        beneficiaryUpdateResponseDto.setStatusCode(200);
        beneficiaryUpdateResponseDto.setMessage("Success");
        Mockito.when(
                beneficiaryService.updateBeneficiary(any())
        ).thenReturn(beneficiaryUpdateResponseDto);
        BeneficiaryUpdateRequestDto updateRequestDto = new BeneficiaryUpdateRequestDto();
        updateRequestDto.setAccountNo(111L);
        updateRequestDto.setAmountLimit(10.0);
        BeneficiaryUpdateResponseDto response = beneficiaryController.updateBeneficiary(updateRequestDto);
        Assert.assertNotNull(response);
    }


}
