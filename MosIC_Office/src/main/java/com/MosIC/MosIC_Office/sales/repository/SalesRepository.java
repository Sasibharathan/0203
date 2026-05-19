package com.MosIC.MosIC_Office.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.MosIC.MosIC_Office.sales.entity.SalesEntity;

public interface SalesRepository extends JpaRepository<SalesEntity, Long> {

}
