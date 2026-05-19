package com.MosIC.MosIC_Office.sales_items.service;

import java.util.List;

import com.MosIC.MosIC_Office.sales_items.dto.SalesItemDTO;

public interface SalesItemService {

    SalesItemDTO createSalesItem(SalesItemDTO salesItemDTO);

    SalesItemDTO getSalesItemById(Long id);

    List<SalesItemDTO> getSalesItemsByRefFileNo(Integer refFileNo);

    SalesItemDTO updateSalesItem(Long id, SalesItemDTO salesItemDTO);

    void deleteSalesItem(Long id);

    void deleteSalesItemsByRefFileNo(Integer refFileNo);

    List<SalesItemDTO> getAllSalesItems();

}