package com.scrotifybanking.payeemanagement.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Customer.
 */
@Table(name = "customer")
@Entity
@NoArgsConstructor
@Data
@SequenceGenerator(name = "seq", initialValue = 1000)
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_password")
    private String customerPassword;

    @Column(name = "customer_dob")
    private LocalDate customerDob;

    @Column(name = "customer_salary")
    private Double customerSalary;

    @Column(name = "customer_age")
    private Integer customerAge;

    @Column(name = "customer_role")
    private String customerRole;

    @Column(name = "customer_mobileno")
    private Long customerMobileNo;

    @Column(name = "customer_city")
    private String customerCity;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Beneficiary> beneficiaries;

}