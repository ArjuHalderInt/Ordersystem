package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ItemDetails;
import com.example.demo.response.TotalBill;
import com.example.demo.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService order;
	
	@GetMapping("/orderlist")
	List<ItemDetails> orderlist() {
		return order.orderlist();
		
	}
	
	@PostMapping("/create-customer")
	String createCustomer(@RequestParam String name, @RequestParam String phone) {
		return order.createCustomer(name, phone);
		
	}
	
	@GetMapping("/orderlist/selectorder")
	String selectOrder(@RequestParam int quantity,@RequestParam long itemId,@RequestParam long customerId ) {
		return order.selectOrder( quantity, itemId, customerId ) ;
	}
	
	@GetMapping("/orderlist/fetchbill")
	public TotalBill fetchBill(@RequestParam long customerId){
		return order.fetchBill(customerId);
	}
	
	@GetMapping("/find_id_by_number")
	public long fetchId(@RequestParam String phone) {
		return order.fetchId(phone);
	}
	
	
}
