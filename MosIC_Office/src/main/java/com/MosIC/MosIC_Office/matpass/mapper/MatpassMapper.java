package com.MosIC.MosIC_Office.matpass.mapper;

import com.MosIC.MosIC_Office.matpass.dto.MatpassDTO;
import com.MosIC.MosIC_Office.matpass.entity.MatpassEntity;

public final class MatpassMapper {

  public static MatpassDTO mapToDTO(MatpassEntity e) {
    if (e == null) {
      return null;
    }

    MatpassDTO dto = new MatpassDTO();
    dto.setId(e.getId());
    dto.setInOrOut(e.getInOrOut());
    dto.setParty(e.getParty());
    dto.setDate(e.getDate());
    dto.setContactPerson(e.getContactPerson());
    dto.setDiscription(e.getDiscription());
    dto.setFileRef(e.getFileRef());
    dto.setStatus(e.getStatus() != null ? e.getStatus() : 1);
    return dto;
  }

  public static MatpassEntity mapToEntity(MatpassDTO d) {
    if (d == null) {
      return null;
    }

    MatpassEntity entity = new MatpassEntity();
    entity.setId(d.getId());
    entity.setInOrOut(d.getInOrOut());
    entity.setParty(d.getParty());
    entity.setDate(d.getDate());
    entity.setContactPerson(d.getContactPerson());
    entity.setDiscription(d.getDiscription());
    entity.setFileRef(d.getFileRef());
    entity.setStatus(d.getStatus() != null ? d.getStatus() : 1);
    return entity;
  }

}
