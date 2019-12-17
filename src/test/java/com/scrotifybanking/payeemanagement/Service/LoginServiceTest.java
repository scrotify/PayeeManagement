package com.scrotifybanking.payeemanagement.Service;

import com.scrotifybanking.payeemanagement.dto.LoginRequestDto;
import com.scrotifybanking.payeemanagement.dto.LoginResponseDto;
import com.scrotifybanking.payeemanagement.entity.Customer;
import com.scrotifybanking.payeemanagement.repository.BankRepository;
import com.scrotifybanking.payeemanagement.repository.CustomerRepository;
import com.scrotifybanking.payeemanagement.service.CustomerServiceImpl;
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
public class LoginServiceTest {

    @Mock
    private BankRepository bankRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void testDeleteBeneficiary() {
        Customer customer = new Customer();
        customer.setCustomerId(111L);
        customer.setCustomerPassword("PPP");
        Optional<Customer> customerOptional = Optional.ofNullable(customer);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setStatusCode(200);
        loginResponseDto.setMessage("Success");
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setId(100L);
        loginRequestDto.setPassword("ppp");
        Mockito.when(customerRepository.findByCustomerId(anyLong())).thenReturn(customerOptional);
        Optional<LoginResponseDto> loginResponseDtoOptional = customerService.loginCustomer(loginRequestDto);
        Assert.assertNotNull(loginResponseDtoOptional);
    }
}
