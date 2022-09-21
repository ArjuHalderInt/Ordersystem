package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.ItemDetails;

public interface ItemRepository extends JpaRepository<ItemDetails, Long> { //crud operations occur here <classname, primaryKeyType>
	
	@Query(value= "SELECT * FROM item_details", nativeQuery= true)
	public List<ItemDetails> getItemList();
}
