package com.MosIC.MosIC_Office.stocks.mapper;

import com.MosIC.MosIC_Office.stocks.dto.StocksDTO;
import com.MosIC.MosIC_Office.stocks.entity.StocksEntity;

public class StocksMapper {

  public static StocksDTO mapToDTO(StocksEntity e) {
    if (e == null) {
      return null;
    }

    StocksDTO dto = new StocksDTO();
    dto.setId(e.getId());
    dto.setStockItemId(e.getStockItemId());
    dto.setStockDate(e.getStockDate());
    dto.setStockDescription(e.getStockDescription());
    dto.setStockInOut(e.getStockInOut());
    dto.setStockQuantity(e.getStockQuantity());
    dto.setStockReturnOrNonReturn(e.getStockReturnOrNonReturn());
    dto.setStockParty(e.getStockParty());
    dto.setMatPassId(e.getMatPassId());
    dto.setStatus(e.getStatus() != null ? e.getStatus() : 1);
    return dto;
  }

  public static StocksEntity mapToEntity(StocksDTO d) {
    if (d == null) {
      return null;
    }

    StocksEntity entity = new StocksEntity();
    entity.setId(d.getId());
    entity.setStockItemId(d.getStockItemId());
    entity.setStockDate(d.getStockDate());
    entity.setStockDescription(d.getStockDescription());
    entity.setStockInOut(d.getStockInOut());
    entity.setStockQuantity(d.getStockQuantity());
    entity.setStockReturnOrNonReturn(d.getStockReturnOrNonReturn());
    entity.setStockParty(d.getStockParty());
    entity.setMatPassId(d.getMatPassId());
    entity.setStatus(d.getStatus() != null ? d.getStatus() : 1);
    return entity;
  }
}
