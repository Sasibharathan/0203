package com.MosIC.MosIC_Office.purchase_items.service;

import com.MosIC.MosIC_Office.purchase_items.dto.PurchaseItemDTO;

import java.util.List;

public interface PurchaseItemService {
    PurchaseItemDTO createPurchaseItem(PurchaseItemDTO purchaseItemDTO);
    PurchaseItemDTO getPurchaseItemById(Long id);
    List<PurchaseItemDTO> getAllPurchaseItems();
    List<PurchaseItemDTO> getPurchaseItemsByRefFileNo(Integer refFileNo);
    PurchaseItemDTO updatePurchaseItem(Long id, PurchaseItemDTO purchaseItemDTO);
    void deletePurchaseItem(Long id);  // soft delete
}