package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.entity.OrdersDetailsEntity;

@Repository
public interface OrdersReponsitory extends JpaRepository<OrdersDetailsEntity, Integer> {

	@Query("select u from OrdersDetailsEntity u where u.customerEntity.customerId = ?1")
	List<OrdersDetailsEntity> findByCustomerId(Integer customerId);

	
}
