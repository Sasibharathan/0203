package com.MosIC.MosIC_Office.purchase.repository;

import com.MosIC.MosIC_Office.purchase.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
    List<PurchaseEntity> findByPurchaseStatus(Integer status);
}