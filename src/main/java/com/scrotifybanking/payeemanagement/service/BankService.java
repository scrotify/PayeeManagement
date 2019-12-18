package com.scrotifybanking.payeemanagement.service;

import com.scrotifybanking.payeemanagement.dto.BankDto;

public interface BankService {
	
	public BankDto getBankByIfscCode(String ifscCode);

}
