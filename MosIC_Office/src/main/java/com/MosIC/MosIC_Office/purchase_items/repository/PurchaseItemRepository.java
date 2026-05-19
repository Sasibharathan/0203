package com.MosIC.MosIC_Office.purchase_items.repository;

import com.MosIC.MosIC_Office.purchase_items.entity.PurchaseItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItemEntity, Long> {
    List<PurchaseItemEntity> findByRefFileNoAndStatus(Integer refFileNo, Integer status);
    List<PurchaseItemEntity> findByStatus(Integer status);
}