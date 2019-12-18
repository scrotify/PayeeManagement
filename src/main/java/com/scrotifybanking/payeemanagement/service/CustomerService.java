package com.scrotifybanking.payeemanagement.service;

import com.scrotifybanking.payeemanagement.dto.LoginRequestDto;
import com.scrotifybanking.payeemanagement.dto.LoginResponseDto;
import com.scrotifybanking.payeemanagement.exception.CustomException;

import java.util.Optional;

/**
 * The interface Customer service.
 *
 * @author Mahendran
 */
public interface CustomerService {

    /**
     * Login customer optional.
     *
     * @param loginRequestDto the login request dto
     * @return the optional
     * @throws CustomException the custom exception
     */
    public Optional<LoginResponseDto> loginCustomer(LoginRequestDto loginRequestDto);


}
