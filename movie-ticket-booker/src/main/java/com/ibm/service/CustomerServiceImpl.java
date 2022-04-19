package com.ibm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.entity.Customer;
import com.ibm.exception.CustomerNotFoundException;
import com.ibm.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	/**
	 * Check the user id is exist or not
	 * 
	 * @param userId
	 * @return userid
	 */
	@Override
	public boolean existsById(Integer userId) {
		return customerRepository.existsById(userId);
	}

	/**
	 * Check the mobile no is exist or not
	 * 
	 * @param mobileNo
	 * @return mobileNo
	 */

	@Override
	public boolean existsByMobileNumber(String mobileNo) {
		return customerRepository.existsByMobileNumber(mobileNo);
	}

	/**
	 * Check the email id is exist or not
	 * 
	 * @param email
	 * @return emailId
	 */

	@Override
	public boolean existsByEmail(String email) {
		return customerRepository.existsByEmail(email);
	}

	/**
	 * Add Customer object is database
	 * 
	 * @param customer
	 * @return Customer
	 * @throws CustomerNotFoundException
	 */

	@Override
	public Customer addCustomer(Customer customer) throws CustomerNotFoundException {
		if (customer != null) {
			if (customerRepository.existsById(customer.getCustomerId())) {
				throw new CustomerNotFoundException("Customer " + customer.getCustomerId() + " is already Exists");
			} else if (customerRepository.existsByMobileNumber(customer.getMobileNumber())) {
				throw new CustomerNotFoundException(
						"Customer with number " + customer.getMobileNumber() + " is already Exists");
			} else if (customerRepository.existsByEmail(customer.getEmail())) {
				throw new CustomerNotFoundException(
						"Customer with email " + customer.getEmail() + " is already Exists");
			} else {
				customerRepository.saveAndFlush(customer);
			}
		}
		return customerRepository.getById(customer.getCustomerId());
	}

	/**
	 * Remove customer from database
	 * 
	 * @param customerid
	 * @return Customer
	 */

	@Override
	public Customer removeCustomer(int customerid) {
		Customer c = customerRepository.getById(customerid);
		customerRepository.delete(c);
		return c;
	}

	/**
	 * Update customer object
	 * 
	 * @param customer
	 * @return Customer
	 */

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		System.out.println(customer.getCustomerId());
		customerRepository.saveAndFlush(customer);
		return customerRepository.getById(customer.getCustomerId());
	}

	/**
	 * View Customer by customerId
	 * 
	 * @param customerid
	 * @return Customer
	 */

	@Override
	public Customer viewCustomer(int customerid) {
		return customerRepository.findById(customerid).get();
	}

	/**
	 * View list of customer
	 * 
	 * @return Customer
	 */

	@Override
	public List<Customer> viewCustomerList() {
		return customerRepository.findAll();
	}

}
