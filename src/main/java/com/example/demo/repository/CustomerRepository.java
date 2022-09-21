package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
 
	@Query(value= "SELECT id from ems.customer where phone=?1", nativeQuery= true)
	public long findIdByPhone(String phone);
}
