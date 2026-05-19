package com.MosIC.MosIC_Office.purchase.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class PurchaseDTO {
  private long id;// ✅ wrapper, allows null
  private String purchaseDate;
  private String purchaseValidity;
  private String purchaseFromParty;
  private String purchaseToParty;
  private String purchaseEnquireDate;
  private String purchaseDeliveryTerms;
  private String purchasePaymentTerms;
  private String purchaseCurrency;
  private String purchaseDoctype;
  private String purchaseTxType;
  private String purchaseDescription;
  private String purchaseAddressedTo;
  private String purchaseFileRef;
  private String purchaseStatus;

}
