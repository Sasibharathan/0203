package com.MosIC.MosIC_Office.sales_items.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MosIC.MosIC_Office.sales_items.entity.SalesItemEntity;

public interface SalesItemRepository extends JpaRepository<SalesItemEntity, Long> {

    // Fetch all items belonging to a specific Sales document
    List<SalesItemEntity> findByRefFileNo(Integer refFileNo);

    // Delete all items belonging to a specific Sales document
    void deleteByRefFileNo(Integer refFileNo);

}