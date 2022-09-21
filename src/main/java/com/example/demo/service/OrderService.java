package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerOrderSave;
import com.example.demo.entity.ItemDetails;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.response.OrderResponse;
import com.example.demo.response.TotalBill;

@Service
public class OrderService {
	@Autowired
	ItemRepository orderT; 										//to interact with repository class, thats why we inject, repository does the CRUD operations

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ItemRepository itemRepository;

	public List<ItemDetails> orderlist() {
		return itemRepository.getItemList();

	}

	public String selectOrder(int quantity, long id, long customerId) {
		Optional<ItemDetails> x = orderT.findById(id);
		double pricestore = x.get().price;
		String itemname = x.get().itemName;

		CustomerOrderSave customerOrderSave = orderRepository.findByCustomerIdAndItemName(customerId, itemname); //in service we do not define the data type in the argument
		
		if(customerOrderSave == null) {
			CustomerOrderSave ordersave = new CustomerOrderSave();
			ordersave.setCustomerId(customerId);
			ordersave.setItemName(itemname);
			ordersave.setItemPrice(pricestore);
			ordersave.setQuantity(quantity);
			orderRepository.save(ordersave);
			return "Your order has been placed";
		}
		else {
			customerOrderSave.setQuantity(quantity + customerOrderSave.quantity);
			orderRepository.save(customerOrderSave);		
		}
		return "Order saved";
		
	}

	public String createCustomer(String name, String phone) { 						//creating customer method
		Customer customer = new Customer(); 										//creating customer object
		customer.setCustomerName(name); 											//setting customer data(name) 
		customer.setPhone(phone); 													//setting customer data(phone) 
		customerRepository.save(customer); 											//saving customer data in the DB with the customerRepository object
		return "Customer created successfully";

	}

	public TotalBill fetchBill(long customerId) {
		List<CustomerOrderSave> orderList = orderRepository.findByCustomerId(customerId);
		List<OrderResponse> orderresponseList = new ArrayList<>();
		double totalBillPrice = 0;
		for (CustomerOrderSave list : orderList) {
			OrderResponse orderresponse = new OrderResponse();
			orderresponse.setItemName(list.itemName);
			orderresponse.setQuantity(list.quantity);
			orderresponse.setTotalPrice(list.quantity * list.itemPrice);
			totalBillPrice = (list.quantity * list.itemPrice) + totalBillPrice;
			orderresponseList.add(orderresponse);
		}
		
		orderRepository.deleteByCustomerId(customerId);
		TotalBill totalbill = new TotalBill();
		totalbill.setOrderResponse(orderresponseList);
		totalbill.setTotalBill(totalBillPrice);
		return totalbill;

	}
	
	public long fetchId(String phone) {  												//the value which needs to be fetched is getting passed through the argument
	
		return customerRepository.findIdByPhone(phone);
		
	}
	
	

}
