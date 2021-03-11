package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.CustomerEntity;

@Repository
public interface CustomerReponsitory extends JpaRepository<CustomerEntity, Integer> {

}
