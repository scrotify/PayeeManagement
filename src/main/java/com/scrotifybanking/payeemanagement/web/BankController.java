package com.scrotifybanking.payeemanagement.web;

import com.scrotifybanking.payeemanagement.dto.BankDto;
import com.scrotifybanking.payeemanagement.service.BankServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Fund transfer controller.
 */
@RestController
@RequestMapping("/bank")
@CrossOrigin
public class BankController {


    private static final Logger logger = LogManager.getLogger(BankController.class);

    @Autowired
    private BankServiceImpl bankService;

    /**
     * Gets bank details by ifsc code.
     *
     * @param ifscCode the ifsc code
     * @return the bank details by ifsc code
     */
    @GetMapping("/search")
    public ResponseEntity<BankDto> getBankDetailsByIfscCode(@RequestParam String ifscCode) {
        logger.info("Get bank details by IFSC code");
        BankDto bankDto = bankService.getBankByIfscCode(ifscCode);
        return new ResponseEntity<>(bankDto, HttpStatus.OK);
    }

}
