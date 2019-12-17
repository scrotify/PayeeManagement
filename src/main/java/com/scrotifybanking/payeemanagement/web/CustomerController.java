package com.scrotifybanking.payeemanagement.web;

import com.scrotifybanking.payeemanagement.dto.LoginRequestDto;
import com.scrotifybanking.payeemanagement.dto.LoginResponseDto;
import com.scrotifybanking.payeemanagement.service.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        logger.info("login controller");
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        Optional<LoginResponseDto> loginResponseDtoOptional = customerService.loginCustomer(loginRequestDto);
        if (loginResponseDtoOptional.isPresent()) {
            logger.info("log response found");
            loginResponseDto = loginResponseDtoOptional.get();
        }
        logger.info("End of login controller");
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }
}
