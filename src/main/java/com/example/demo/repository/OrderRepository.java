package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerOrderSave;

@Transactional
public interface OrderRepository  extends JpaRepository<CustomerOrderSave, Long> {

	List <CustomerOrderSave> findByCustomerId(long customerId);
	CustomerOrderSave findByCustomerIdAndItemName(long customerId, String itemName); // if the method is not in repo, we create custom methods, the needed parameters passed in the arguments  
	long deleteByCustomerId(long customerId);
	
}