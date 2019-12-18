package com.scrotifybanking.payeemanagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "banks")
@NoArgsConstructor
@Setter
@Getter
@SequenceGenerator(name = "seq", initialValue = 2000)
public class Bank implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "bank_id")
    private Long bankId;

    @Column(name = "bank_branch")
    private String bankBranch;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_ifsccode")
    private String bankIfscCode;

    @Column(name = "bank_pincode")
    private Long bankPincode;

    @Column(name = "bank_address")
    private String bankAddress;
}
