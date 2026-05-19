package com.MosIC.MosIC_Office.stocksItem.service;

import com.MosIC.MosIC_Office.stocksItem.dto.StockItemDTO;

import java.util.List;

public interface StockItemService {

    StockItemDTO createStockItem(StockItemDTO stockItemDTO);

    StockItemDTO getStockItemById(Long id);

    List<StockItemDTO> getAllStockItems();

    List<StockItemDTO> getStockItemsByStatus(Integer status);

    List<StockItemDTO> searchStockItemsByName(String productName);

    StockItemDTO updateStockItem(Long id, StockItemDTO stockItemDTO);

    void deleteStockItem(Long id);  // soft delete — sets status to 0
}