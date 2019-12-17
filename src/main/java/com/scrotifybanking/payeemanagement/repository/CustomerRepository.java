package com.scrotifybanking.payeemanagement.repository;

import com.scrotifybanking.payeemanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Customer Repository has one method.
 *
 * @author anishaR
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	/**
	 * Find by customer id optional.
	 *
	 * @param id the id
	 * @return the optional
	 */
	Optional<Customer> findByCustomerId(Long id);

}