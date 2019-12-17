package com.scrotifybanking.payeemanagement.web;

import com.scrotifybanking.payeemanagement.dto.ApiResponse;
import com.scrotifybanking.payeemanagement.dto.DeleteBeneficiaryDto;
import com.scrotifybanking.payeemanagement.service.BeneficiaryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Beneficiary controller.
 */
@RestController
@RequestMapping("/payee")
@CrossOrigin
public class BeneficiaryController {

    private static final Logger logger = LoggerFactory.getLogger(BeneficiaryController.class);
    @Autowired
    private BeneficiaryServiceImpl beneficiaryService;

    /**
     * Delete beneficiary response entity.
     *
     * @param deleteBeneficiaryDto the delete beneficiary dto
     * @return the response entity
     */
    @DeleteMapping("/beneficiaries")
    public ResponseEntity<ApiResponse> deleteBeneficiary(@RequestBody DeleteBeneficiaryDto deleteBeneficiaryDto) {
        ApiResponse response = new ApiResponse();
        logger.info("Entering into delete Beneficiary ID :" + deleteBeneficiaryDto.getBeneficiaryId());
        beneficiaryService.deleteBeneficiaryById(deleteBeneficiaryDto.getBeneficiaryId(), deleteBeneficiaryDto.getCustomerId());
        response.setMessage(com.scrotifybanking.payeemanagement.web.ScrotifyConstant.SUCCESS_MESSAGE);
        response.setStatusCode(com.scrotifybanking.payeemanagement.web.ScrotifyConstant.SUCCESS_CODE);
        logger.info("Deleted successfully ");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

