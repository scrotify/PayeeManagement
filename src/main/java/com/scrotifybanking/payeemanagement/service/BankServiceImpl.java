package com.scrotifybanking.payeemanagement.service;

import com.scrotifybanking.payeemanagement.dto.BankDto;
import com.scrotifybanking.payeemanagement.entity.Bank;
import com.scrotifybanking.payeemanagement.exception.CustomException;
import com.scrotifybanking.payeemanagement.repository.BankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankServiceImpl {

    public static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);
    private BankRepository bankRepository;

    public BankDto getBankByIfscCode(String ifscCode) {
        logger.info("BankServiceImpl get Bank details by IFSC Code");
        BankDto bankDto = new BankDto();
        Bank bank = new Bank();
        List<BankDto> bankDtos = null;
        Optional<Bank> bankIfscCodeOptional = bankRepository.findByBankIfscCode(ifscCode);
        if (bankIfscCodeOptional.isPresent()) {
            bank = bankIfscCodeOptional.get();
            BeanUtils.copyProperties(bank, bankDto);
        }
        bankIfscCodeOptional.orElseThrow(CustomException::new);
        logger.info("End of BankServiceImpl get Bank details by IFSC Code");
        return bankDto;
    }
}
