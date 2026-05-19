package com.MosIC.MosIC_Office.sales.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SalesDTO {

  private long id;// long wrapper, allows null
  private String salesDate;
  private String salesValidity;
  private String salesFromParty;
  private String salesToParty;
  private String salesEnquireDate;
  private String salesDeliveryTerms;
  private String salesPaymentTerms;
  private String salesCurrency;
  private String salesDoctype;
  private String salesTxType;
  private String salesDescription;
  private String salesAddressedTo;
  private String salesFileRef;
  private String salesStatus;


}