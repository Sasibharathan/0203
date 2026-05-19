package com.MosIC.MosIC_Office.stocks.repository;

import com.MosIC.MosIC_Office.stocks.entity.StocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StocksRepository extends JpaRepository<StocksEntity, Long> {

    List<StocksEntity> findByStatus(Integer status);

    List<StocksEntity> findByStockItemId(Long stockItemId);

    List<StocksEntity> findByMatPassId(Long matPassId);

    List<StocksEntity> findByStockInOut(String stockInOut);

    List<StocksEntity> findByStockPartyContainingIgnoreCase(String stockParty);
}