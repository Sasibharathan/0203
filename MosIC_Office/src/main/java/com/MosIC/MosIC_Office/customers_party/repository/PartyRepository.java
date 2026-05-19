package com.MosIC.MosIC_Office.customers_party.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MosIC.MosIC_Office.customers_party.entity.PartyEntity;

public interface PartyRepository extends JpaRepository<PartyEntity, Long> {
   List<PartyEntity> findByCustomer_Id(Long customerId);
}
