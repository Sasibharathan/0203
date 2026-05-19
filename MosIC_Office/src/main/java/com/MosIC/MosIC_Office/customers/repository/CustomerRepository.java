package com.MosIC.MosIC_Office.customers.repository;

import com.MosIC.MosIC_Office.customers.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}