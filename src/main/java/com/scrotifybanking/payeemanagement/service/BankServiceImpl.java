package com.scrotifybanking.payeemanagement.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrotifybanking.payeemanagement.dto.BankDto;
import com.scrotifybanking.payeemanagement.entity.Bank;
import com.scrotifybanking.payeemanagement.exception.CustomException;
import com.scrotifybanking.payeemanagement.repository.BankRepository;

/**
 * The type Bank ServiceImpl
 * @author Mahendran
 *
 */
@Service
public class BankServiceImpl {

	public static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

	@Autowired
	private BankRepository bankRepository;

	/**
	 * This method is used to get bank details by using ifscCode 
	 * @param ifscCode
	 * @return
	 */
	public BankDto getBankByIfscCode(String ifscCode) {
		logger.info("BankServiceImpl get Bank details by IFSC Code");
		BankDto bankDto = new BankDto();
		List<BankDto> bankDtos = null;
		Optional<Bank> bankIfscCodeOptional = bankRepository.findByBankIfscCode(ifscCode);
		bankIfscCodeOptional.ifPresent(bank -> {
			BeanUtils.copyProperties(bank, bankDto);
		});
		bankIfscCodeOptional.orElseThrow(CustomException::new);
		logger.info("End of BankServiceImpl get Bank details by IFSC Code");
		return bankDto;
	}
}
