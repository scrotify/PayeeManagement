package com.scrotifybanking.payeemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The type Account.
 */
@Entity
@Table(name = "beneficiary")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "seq", initialValue = 30001)
public class Beneficiary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "beneficiary_id")
	private Long BeneficiaryId;

	@Column(name = "beneficiary_account_no")
	private Long BeneficiaryAccountNumber;

	@Column(name = "amount_limit")
	private Double amountLimit;

	@Column(name = "beneficary_name")
	private String beneficaryName;

	@Column(name = "nick_name")
	private String nickName;
	@Column(name = "bank_name")
	private String bankName;
	@Column(name = "ibank_ifsccode")
	private String bankIfscCode;
	@Column(name="customer_id")
	private Long customerId;
}