package com.MosIC.MosIC_Office.purchase.mapper;

import com.MosIC.MosIC_Office.purchase.dto.PurchaseDTO;
import com.MosIC.MosIC_Office.purchase.entity.PurchaseEntity;

public class PurchaseMapper {
  public static PurchaseDTO mapToDTO(PurchaseEntity purchaseEntity) {
    return new PurchaseDTO(
      purchaseEntity.getId(),
      purchaseEntity.getPurchaseDate(),
      purchaseEntity.getPurchaseValidity(),
      purchaseEntity.getPurchaseFromParty(),
      purchaseEntity.getPurchaseToParty(),
      purchaseEntity.getPurchaseEnquireDate(),
      purchaseEntity.getPurchaseDeliveryTerms(),
      purchaseEntity.getPurchasePaymentTerms(),
      purchaseEntity.getPurchaseCurrency(),
      purchaseEntity.getPurchaseDoctype().toString(),
      purchaseEntity.getPurchaseTxType(),
      purchaseEntity.getPurchaseDescription(),
      purchaseEntity.getPurchaseAddressedTo(),
      purchaseEntity.getPurchaseFileRef(),
     // purchaseEntity.getPurchaseStatus()
     String.valueOf(purchaseEntity.getPurchaseStatus())
    );
  }

  public static PurchaseEntity mapToEntity(PurchaseDTO purchaseDTO) {
    return new PurchaseEntity(
      purchaseDTO.getId(),
      purchaseDTO.getPurchaseDate(),
      purchaseDTO.getPurchaseValidity(),
      purchaseDTO.getPurchaseFromParty(),
      purchaseDTO.getPurchaseToParty(),
      purchaseDTO.getPurchaseEnquireDate(),
      purchaseDTO.getPurchaseDeliveryTerms(),
      purchaseDTO.getPurchasePaymentTerms(),
      purchaseDTO.getPurchaseCurrency(),
      //purchaseDTO.getPurchaseDoctype(),
      Integer.parseInt(purchaseDTO.getPurchaseDoctype()),
      purchaseDTO.getPurchaseTxType(),
      purchaseDTO.getPurchaseDescription(),
      purchaseDTO.getPurchaseAddressedTo(),
      purchaseDTO.getPurchaseFileRef(),
      //purchaseDTO.getPurchaseStatus()
      Integer.parseInt(purchaseDTO.getPurchaseStatus())
    );
  }


}
