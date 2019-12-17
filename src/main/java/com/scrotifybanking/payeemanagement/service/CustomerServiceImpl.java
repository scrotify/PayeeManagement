package com.scrotifybanking.payeemanagement.service;


import com.scrotifybanking.payeemanagement.dto.LoginRequestDto;
import com.scrotifybanking.payeemanagement.dto.LoginResponseDto;
import com.scrotifybanking.payeemanagement.entity.Customer;
import com.scrotifybanking.payeemanagement.exception.CustomException;
import com.scrotifybanking.payeemanagement.repository.CustomerRepository;
import com.scrotifybanking.payeemanagement.util.ScrotifyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Customer service.
 *
 * @author Mahendran
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    public static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    /**
     * The Customer repository.
     */
    @Autowired
    CustomerRepository customerRepository;

    /**
     * This method is used to login a customer and admin
     */
    @Override
    public Optional<LoginResponseDto> loginCustomer(LoginRequestDto loginRequestDto) throws CustomException {
        logger.info("Login customer ");
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        Optional<LoginResponseDto> loginResponseDtoOptional = Optional.empty();
        Long customerId = loginRequestDto.getId();
        String password = loginRequestDto.getPassword();
        Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
        if (customer.isPresent()) {
            logger.info("Login customer present");
            loginResponseDtoOptional = customer
                    .filter(cust -> cust.getCustomerId().equals(customerId))
                    .filter(cust -> cust.getCustomerPassword().equals(password))
                    .map(cust -> {
                        BeanUtils.copyProperties(cust, loginResponseDto);
                        return loginResponseDto;
                    });
            loginResponseDto.setStatusCode(ScrotifyConstant.SUCCESS_CODE);
            loginResponseDto.setMessage(ScrotifyConstant.SUCCESS_MESSAGE);
        } else {
            logger.info("Login customer not present");
            loginResponseDto.setStatusCode(ScrotifyConstant.FAILURE_CODE);
            loginResponseDto.setMessage(ScrotifyConstant.INVALID_MESSAGE);
        }
        return loginResponseDtoOptional;
    }
}
