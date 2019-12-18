package com.scrotifybanking.payeemanagement.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scrotifybanking.payeemanagement.dto.ApiResponse;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryAddResponseDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateRequestDto;
import com.scrotifybanking.payeemanagement.dto.BeneficiaryUpdateResponseDto;
import com.scrotifybanking.payeemanagement.dto.DeleteBeneficiaryDto;
import com.scrotifybanking.payeemanagement.dto.ListBeneficiaryDto;
import com.scrotifybanking.payeemanagement.exception.CustomerNotFoundException;
import com.scrotifybanking.payeemanagement.exception.InvalidBankException;
import com.scrotifybanking.payeemanagement.service.BeneficiaryService;
import com.scrotifybanking.payeemanagement.util.ScrotifyConstant;

/**
 * The type Beneficiary Controller
 * @author 
 *
 */
@RestController
@RequestMapping("/beneficiaries")
@CrossOrigin
public class BeneficiaryController {
	
	 private static final Logger logger = LoggerFactory.getLogger(BeneficiaryController.class);
	 
	@Autowired
	BeneficiaryService beneficiaryService;

	/**
	 * This method is used to add the beneficiary account for the customer
	 * @param customerId
	 * @param beneficiaryAddRequestDto
	 * @return beneficiaryAddResponseDto
	 * @throws InvalidBankException
	 * @throws CustomerNotFoundException
	 */
	@PostMapping("/{customerId}")
	public BeneficiaryAddResponseDto addBeneficiaryAccount(@PathVariable Long customerId,
			@RequestBody BeneficiaryAddRequestDto beneficiaryAddRequestDto)
			throws InvalidBankException, CustomerNotFoundException {
		logger.info("Entering into add beneficiary account controller method");
		return beneficiaryService.addBeneficiary(customerId, beneficiaryAddRequestDto);
	}
	
	/**
	 * This method is used to list the beneficiary details 
	 * @param customerId
	 * @return the response entity
	 */
	@GetMapping("/{customerId}")
	public ResponseEntity<List<ListBeneficiaryDto>> viewBeneficiaries(@PathVariable("customerId") Long customerId) {
		logger.info("Entering into view beneficiary account controller method");
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
    public ResponseEntity<ApiResponse> deleteBeneficiary(@RequestParam  Long customerId, @RequestParam Long beneficiaryId) {
        ApiResponse response = new ApiResponse();
        DeleteBeneficiaryDto deleteBeneficiaryDto = new DeleteBeneficiaryDto();
        deleteBeneficiaryDto.setBeneficiaryId(beneficiaryId);
        deleteBeneficiaryDto.setCustomerId(customerId);
        logger.info("Entering into delete Beneficiary ID :" + deleteBeneficiaryDto.getBeneficiaryId());
        Optional<Boolean> deletedSrc = beneficiaryService.deleteBeneficiaryById(deleteBeneficiaryDto.getBeneficiaryId(), deleteBeneficiaryDto.getCustomerId());
        if ( deletedSrc.isPresent() ) {
         response.setMessage(ScrotifyConstant.SUCCESS_MESSAGE);
         response.setStatusCode(ScrotifyConstant.SUCCESS_CODE);
         logger.info("Deleted successfully ");
        } else {
            response.setMessage(ScrotifyConstant.FAILURE_MESSAGE);
            response.setStatusCode(ScrotifyConstant.FAILURE_CODE);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Update beneficiary beneficiary update response dto.
     *
     * @param beneficiaryUpdateRequestDto the beneficiary update request dto
     * @return the beneficiary update response dto
     * @throws Exception the exception
     */
    @PutMapping("")
    public BeneficiaryUpdateResponseDto updateBeneficiary(@RequestBody BeneficiaryUpdateRequestDto beneficiaryUpdateRequestDto)
            throws Exception {
    	logger.info("Entering into update beneficiary account controller method");
        BeneficiaryUpdateResponseDto beneficiaryUpdateResponseDto = beneficiaryService.updateBeneficiary(beneficiaryUpdateRequestDto);
        beneficiaryUpdateResponseDto.setMessage(ScrotifyConstant.UPDATED);
        beneficiaryUpdateResponseDto.setStatusCode(201);
        return beneficiaryUpdateResponseDto;
    }



}
