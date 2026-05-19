package com.MosIC.MosIC_Office.purchase.service;

import com.MosIC.MosIC_Office.purchase.dto.PurchaseDTO;
import java.util.List;

public interface PurchaseServices {
    PurchaseDTO createPurchase(PurchaseDTO purchaseDTO);
    PurchaseDTO getPurchaseById(Long id);
    List<PurchaseDTO> getAllPurchases();
    PurchaseDTO updatePurchase(Long id, PurchaseDTO purchaseDTO);
    void deletePurchase(Long id);  // soft delete (status = 0)
}