package com.scrotifybanking.payeemanagement.web;

import com.scrotifybanking.payeemanagement.dto.LoginRequestDto;
import com.scrotifybanking.payeemanagement.dto.LoginResponseDto;
import com.scrotifybanking.payeemanagement.service.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    private CustomerServiceImpl customerService;

    @Test
    public void testLogin() {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setStatusCode(200);
        loginResponseDto.setMessage("Success");
        Optional<LoginResponseDto> loginResponseDtoOptional = Optional.ofNullable(loginResponseDto);
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        Mockito.when(
                customerService.loginCustomer(loginRequestDto)
        ).thenReturn(loginResponseDtoOptional);
        ResponseEntity<LoginResponseDto> response = customerController.login(loginRequestDto);
        Assert.assertNotNull(response);
    }
}
