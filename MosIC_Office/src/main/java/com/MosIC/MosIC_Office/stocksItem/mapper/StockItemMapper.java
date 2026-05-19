package com.MosIC.MosIC_Office.stocksItem.mapper;

import com.MosIC.MosIC_Office.stocksItem.dto.StockItemDTO;
import com.MosIC.MosIC_Office.stocksItem.entity.StockItemEntity;

public class StockItemMapper {

  public static StockItemDTO mapToDTO(StockItemEntity e) {
    if (e == null) {
      return null;
    }

    StockItemDTO dto = new StockItemDTO();

    dto.setId(e.getId());
    dto.setProductName(e.getProductName());
    dto.setOpeningDate(e.getOpeningDate());
    dto.setSmDescription(e.getSmDescription());
    dto.setSmUnit(e.getSmUnit());
    dto.setSmOpeningBalance(e.getSmOpeningBalance());
    dto.setStatus(e.getStatus() != null ? e.getStatus() : 1);
    return dto;
  }
  public static StockItemEntity mapToEntity(StockItemDTO d) {
    if (d == null) {
      return null;
    }

    StockItemEntity entity = new StockItemEntity();

    entity.setId(d.getId());
    entity.setProductName(d.getProductName());
    entity.setOpeningDate(d.getOpeningDate());
    entity.setSmDescription(d.getSmDescription());
    entity.setSmUnit(d.getSmUnit());
    entity.setSmOpeningBalance(d.getSmOpeningBalance());
    entity.setStatus(d.getStatus() != null ? d.getStatus() : 1);
    return entity;
  }
}
