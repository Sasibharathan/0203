package com.MosIC.MosIC_Office.stocksItem.repository;

import com.MosIC.MosIC_Office.stocksItem.entity.StockItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockItemRepository extends JpaRepository<StockItemEntity, Long> {

    List<StockItemEntity> findByStatus(Integer status);

    List<StockItemEntity> findByProductNameContainingIgnoreCase(String productName);

    List<StockItemEntity> findBySmUnit(String smUnit);
}