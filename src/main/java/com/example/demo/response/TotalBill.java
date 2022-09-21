package com.example.demo.response;

import java.util.List;

public class TotalBill {

	List<OrderResponse> orderResponse;
	double totalBill;
	public List<OrderResponse> getOrderResponse() {
		return orderResponse;
	}
	public void setOrderResponse(List<OrderResponse> orderResponse) {
		this.orderResponse = orderResponse;
	}
	public double getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(double totalBill) {
		this.totalBill = totalBill;
	}
	
}
