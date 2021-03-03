package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.ProductEntity;

@Repository
public interface ProductReponsitory extends JpaRepository<ProductEntity, Integer>{

	ProductEntity findByProductId(int productId);
}
