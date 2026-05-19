package com.MosIC.MosIC_Office.customers_party.mapper;

import com.MosIC.MosIC_Office.customers.entity.CustomerEntity;
import com.MosIC.MosIC_Office.customers_party.dto.PartyDTO;
import com.MosIC.MosIC_Office.customers_party.entity.PartyEntity;

public class PartyMapper {

  public static PartyDTO mapToDTO(PartyEntity e) {
    PartyDTO dto = new PartyDTO();
    dto.setId(e.getId());
    dto.setPartyName(e.getPartyName());
    dto.setPartyPhoneno(e.getPartyPhoneno());
    dto.setPartyEmail(e.getPartyEmail());
    dto.setStatus(e.getStatus());

    if (e.getCustomer() != null) {
      dto.setCustomerId(e.getCustomer().getId());
      dto.setCustomerName(e.getCustomer().getName());
    }

    return dto;
  }

  public static PartyEntity mapToEntity(PartyDTO d, CustomerEntity customer) {
    PartyEntity entity = new PartyEntity();
    entity.setId(d.getId());
    entity.setCustomer(customer);
    entity.setPartyName(d.getPartyName());
    entity.setPartyPhoneno(d.getPartyPhoneno());
    entity.setPartyEmail(d.getPartyEmail());
    entity.setStatus(d.getStatus());
    return entity;
  }
}
