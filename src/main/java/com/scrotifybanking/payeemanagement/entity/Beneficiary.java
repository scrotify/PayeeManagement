package com.scrotifybanking.payeemanagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Account.
 */
@Entity
@Table(name = "beneficiary")
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "seq", initialValue = 30001)
public class Beneficiary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "beneficiary_id")
	private Long beneficiaryId;

	@Column(name = "beneficiary_account_no")
	private Long beneficiaryAccountNumber;

	@Column(name = "amount_limit")
	private Double amountLimit;

	@Column(name = "beneficary_name")
	private String beneficaryName;

	@Column(name = "nick_name")
	private String nickName;

	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "bank_ifsc_code")
	private String bankIfscCode;

	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;


}