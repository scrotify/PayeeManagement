package com.scrotifybanking.payeemanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.scrotifybanking.payeemanagement.dto.BankDto;
import com.scrotifybanking.payeemanagement.entity.Bank;
import com.scrotifybanking.payeemanagement.exception.CustomException;
import com.scrotifybanking.payeemanagement.repository.BankRepository;

@Service
public class BankServiceImpl {

    private BankRepository bankRepository;

    public BankDto getBankByIfscCode(String ifscCode) {
        BankDto bankDto = new BankDto();
        List<BankDto> bankDtos = null;
        Optional<List<Bank>> bankIfscCodeOptional = bankRepository.findByBankIfscCodeContains(ifscCode);

        if (bankIfscCodeOptional.isPresent()) {
            bankDtos = bankIfscCodeOptional.get().stream().map(bank -> {
                BeanUtils.copyProperties(bank, bankDto);
                return bankDto;
            }).collect(Collectors.toList());
        }
        bankIfscCodeOptional.orElseThrow(CustomException::new);
        return bankDto;
    }
}
