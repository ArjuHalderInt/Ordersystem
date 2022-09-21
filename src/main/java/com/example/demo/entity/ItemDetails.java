package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ItemDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public String itemName;
	public double price;
	
	
}
