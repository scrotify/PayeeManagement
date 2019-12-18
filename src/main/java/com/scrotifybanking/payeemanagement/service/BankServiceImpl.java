package com.scrotifybanking.payeemanagement.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrotifybanking.payeemanagement.dto.BankDto;
import com.scrotifybanking.payeemanagement.entity.Bank;
import com.scrotifybanking.payeemanagement.exception.CustomException;
import com.scrotifybanking.payeemanagement.repository.BankRepository;
import com.scrotifybanking.payeemanagement.util.ScrotifyConstant;

/**
 * The type Bank ServiceImpl
 * 
 * @author Mahendran
 *
 */
@Service
public class BankServiceImpl implements BankService {

	public static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

	@Autowired
	private BankRepository bankRepository;

	/**
	 * This method is used to get bank details by using ifscCode
	 * 
	 * @param ifscCode
	 * @return
	 */
	public BankDto getBankByIfscCode(String ifscCode) {
		logger.info("BankServiceImpl get Bank details by IFSC Code");
		BankDto bankDto = new BankDto();
		Bank bank = new Bank();
		Optional<Bank> bankIfscCodeOptional = bankRepository.findByBankIfscCode(ifscCode);
		if (bankIfscCodeOptional.isPresent()) {
			bankDto.setBankAddress(bank.getBankAddress());
			bankDto.setBankBranch(bank.getBankBranch());
			bankDto.setBankId(bank.getBankId());
			bankDto.setBankIfscCode(bank.getBankIfscCode());
			bankDto.setBankName(bank.getBankName());
			bankDto.setBankPincode(bank.getBankPincode());
		} else {
			throw new CustomException(ScrotifyConstant.INVALID_BANK);
		}
		logger.info("End of BankServiceImpl get Bank details by IFSC Code");
		return bankDto;
	}
}
