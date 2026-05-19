package com.MosIC.MosIC_Office.sales_items.mapper;

import com.MosIC.MosIC_Office.sales_items.dto.SalesItemDTO;
import com.MosIC.MosIC_Office.sales_items.entity.SalesItemEntity;

public class SalesItemMapper {

  public static SalesItemDTO mapToDTO(SalesItemEntity salesItemEntity) {
    return new SalesItemDTO(
      salesItemEntity.getId(),
      salesItemEntity.getNameOfProductService(),
      salesItemEntity.getHsnAcs(),
      salesItemEntity.getQuantity(),
      salesItemEntity.getUnit(),
      salesItemEntity.getUnitRate(),
      salesItemEntity.getTaxableValue(),
      salesItemEntity.getCgstRate(),
      salesItemEntity.getCgstAmount(),
      salesItemEntity.getSgstRate(),
      salesItemEntity.getSgstAmount(),
      salesItemEntity.getIgstRate(),
      salesItemEntity.getIgstAmount(),
      salesItemEntity.getTotal(),
      salesItemEntity.getRefFileNo(),
      salesItemEntity.getStatus()
    );
  }

  public static SalesItemEntity mapToEntity(SalesItemDTO salesItemDTO) {
    return new SalesItemEntity(
      salesItemDTO.getId(),
      salesItemDTO.getNameOfProductService(),
      salesItemDTO.getHsnAcs(),
      salesItemDTO.getQuantity(),
      salesItemDTO.getUnit(),
      salesItemDTO.getUnitRate(),
      salesItemDTO.getTaxableValue(),
      salesItemDTO.getCgstRate(),
      salesItemDTO.getCgstAmount(),
      salesItemDTO.getSgstRate(),
      salesItemDTO.getSgstAmount(),
      salesItemDTO.getIgstRate(),
      salesItemDTO.getIgstAmount(),
      salesItemDTO.getTotal(),
      salesItemDTO.getRefFileNo(),
      salesItemDTO.getStatus()
    );
  }
}
