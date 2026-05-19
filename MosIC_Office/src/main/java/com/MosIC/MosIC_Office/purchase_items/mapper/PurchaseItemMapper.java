package com.MosIC.MosIC_Office.purchase_items.mapper;

import com.MosIC.MosIC_Office.purchase_items.dto.PurchaseItemDTO;
import com.MosIC.MosIC_Office.purchase_items.entity.PurchaseItemEntity;

public class PurchaseItemMapper {

    public static PurchaseItemDTO mapToDTO(PurchaseItemEntity entity) {
        return new PurchaseItemDTO(
            entity.getId(),
            entity.getNameOfProductService(),
            entity.getHsnAcs(),
            entity.getQuantity(),
            entity.getUnit(),
            entity.getUnitRate(),
            entity.getTaxableValue(),
            entity.getCgstRate(),
            entity.getCgstAmount(),
            entity.getSgstRate(),
            entity.getSgstAmount(),
            entity.getIgstRate(),
            entity.getIgstAmount(),
            entity.getTotal(),
            entity.getRefFileNo(),
            entity.getStatus()
        );
    }

    public static PurchaseItemEntity mapToEntity(PurchaseItemDTO dto) {
        return new PurchaseItemEntity(
            dto.getId(),
            dto.getNameOfProductService(),
            dto.getHsnAcs(),
            dto.getQuantity(),
            dto.getUnit(),
            dto.getUnitRate(),
            dto.getTaxableValue(),
            dto.getCgstRate(),
            dto.getCgstAmount(),
            dto.getSgstRate(),
            dto.getSgstAmount(),
            dto.getIgstRate(),
            dto.getIgstAmount(),
            dto.getTotal(),
            dto.getRefFileNo(),
            dto.getStatus()
        );
    }
}