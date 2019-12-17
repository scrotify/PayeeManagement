package com.scrotifybanking.payeemanagement.web;

import com.scrotifybanking.payeemanagement.dto.*;
import com.scrotifybanking.payeemanagement.exception.CustomerNotFoundException;
import com.scrotifybanking.payeemanagement.exception.InvalidBankException;
import com.scrotifybanking.payeemanagement.service.BeneficiaryServiceImpl;
import com.scrotifybanking.payeemanagement.util.ScrotifyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiaries")
@CrossOrigin
public class BeneficiaryController {

    private static final Logger logger = LoggerFactory.getLogger(BeneficiaryController.class);

    @Autowired
    private BeneficiaryServiceImpl beneficiaryService;

    @PostMapping("/{customerId}")
    public BeneficiaryAddResponseDto addBeneficiaryAccount(@PathVariable Long customerId,
                                                           @RequestBody BeneficiaryAddRequestDto beneficiaryAddRequestDto)
            throws InvalidBankException, CustomerNotFoundException {
        return beneficiaryService.addBeneficiary(customerId, beneficiaryAddRequestDto);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<ListBeneficiaryDto>> viewBeneficiaries(@PathVariable("customerId") Long customerId) {
        List<ListBeneficiaryDto> listBeneficiaryDto = beneficiaryService.viewBeneficiaries(customerId);
        return new ResponseEntity<>(listBeneficiaryDto, HttpStatus.OK);
    }


    /**
     * Delete beneficiary response entity.
     *
     * @param deleteBeneficiaryDto the delete beneficiary dto
     * @return the response entity
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteBeneficiary(@RequestBody DeleteBeneficiaryDto deleteBeneficiaryDto) {
        ApiResponse response = new ApiResponse();
        logger.info("Entering into delete Beneficiary ID :" + deleteBeneficiaryDto.getBeneficiaryId());
        beneficiaryService.deleteBeneficiaryById(deleteBeneficiaryDto.getBeneficiaryId(), deleteBeneficiaryDto.getCustomerId());
        response.setMessage(ScrotifyConstant.SUCCESS_MESSAGE);
        response.setStatusCode(ScrotifyConstant.SUCCESS_CODE);
        logger.info("Deleted successfully ");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Update beneficiary beneficiary update response dto.
     *
     * @param beneficiaryUpdateRequestDto the beneficiary update request dto
     * @return the beneficiary update response dto
     * @throws Exception the exception
     */
    @PutMapping
    public BeneficiaryUpdateResponseDto updateBeneficiary(@RequestBody BeneficiaryUpdateRequestDto beneficiaryUpdateRequestDto)
            throws Exception {
        BeneficiaryUpdateResponseDto beneficiaryUpdateResponseDto = beneficiaryService.updateBeneficiary(beneficiaryUpdateRequestDto);
        beneficiaryUpdateResponseDto.setMessage("updated successfully");
        beneficiaryUpdateResponseDto.setStatusCode(201);
        return beneficiaryUpdateResponseDto;
    }


}
