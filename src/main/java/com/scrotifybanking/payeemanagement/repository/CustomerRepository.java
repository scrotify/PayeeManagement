package com.scrotifybanking.payeemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrotifybanking.payeemanagement.entity.Customer;

/**
 * Customer Repository has one method.
 *
 * @author anishaR
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	/**
	 * Customer Repository has one method and it is finding by the mobile No.
	 *
	 * @param mobileNo the mobile no
	 * @return optional optional
	 */
	Customer findByCustomerMobileNo(Long customerMobileNo);

	/**
	 * Find by customer id optional.
	 *
	 * Find by customer id optional.
	 *
	 * @param id the id
	 * @return the optional
	 */
	Optional<Customer> findByCustomerId(Long id);
}