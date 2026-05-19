package com.MosIC.MosIC_Office.stocks.service;

import com.MosIC.MosIC_Office.stocks.dto.StocksDTO;

import java.util.List;

public interface StocksService {

    StocksDTO createStock(StocksDTO stocksDTO);

    StocksDTO getStockById(Long id);

    List<StocksDTO> getAllStocks();

    List<StocksDTO> getStocksByStatus(Integer status);

    List<StocksDTO> getStocksByStockItemId(Long stockItemId);

    List<StocksDTO> getStocksByMatPassId(Long matPassId);

    List<StocksDTO> getStocksByInOut(String stockInOut);

    StocksDTO updateStock(Long id, StocksDTO stocksDTO);

    void deleteStock(Long id);  // soft delete — sets status to 0
}