package com.MosIC.MosIC_Office.sales.mapper;

import com.MosIC.MosIC_Office.sales.dto.SalesDTO;
import com.MosIC.MosIC_Office.sales.entity.SalesEntity;

public class SalesMapper {

  public static SalesDTO mapToDTO(SalesEntity salesEntity) {
    return new SalesDTO(
      salesEntity.getId(),
      salesEntity.getSalesDate(),
      salesEntity.getSalesValidity(),
      salesEntity.getSalesFromParty(),
      salesEntity.getSalesToParty(),
      salesEntity.getSalesEnquireDate(),
      salesEntity.getSalesDeliveryTerms(),
      salesEntity.getSalesPaymentTerms(),
      salesEntity.getSalesCurrency(),
      salesEntity.getSalesDoctype(),
      salesEntity.getSalesTxType(),
      salesEntity.getSalesDescription(),
      salesEntity.getSalesAddressedTo(),
      salesEntity.getSalesFileRef(),
      salesEntity.getSalesStatus()
    );
  }

  public static SalesEntity mapToEntity(SalesDTO salesDTO) {
    return new SalesEntity(
      salesDTO.getId(),
      salesDTO.getSalesDate(),
      salesDTO.getSalesValidity(),
      salesDTO.getSalesFromParty(),
      salesDTO.getSalesToParty(),
      salesDTO.getSalesEnquireDate(),
      salesDTO.getSalesDeliveryTerms(),
      salesDTO.getSalesPaymentTerms(),
      salesDTO.getSalesCurrency(),
      salesDTO.getSalesDoctype(),
      salesDTO.getSalesTxType(),
      salesDTO.getSalesDescription(),
      salesDTO.getSalesAddressedTo(),
      salesDTO.getSalesFileRef(),
      salesDTO.getSalesStatus()
    );
  }


}
