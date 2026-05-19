package com.MosIC.MosIC_Office.employee_position.mapper;

import com.MosIC.MosIC_Office.employee_position.dto.EmpPositionDTO;
import com.MosIC.MosIC_Office.employee_position.entity.EmpPositionEntity;

public class EmpPositionMapper {

    // DTO → Entity
    public static EmpPositionEntity toEntity(EmpPositionDTO dto) {
        if (dto == null) return null;

        EmpPositionEntity entity = new EmpPositionEntity();
        entity.setId(dto.getId());
        entity.setEmpId(dto.getEmpId());
        entity.setEpDate(dto.getEpDate());
        entity.setEpEfficientDate(dto.getEpEfficientDate());
        entity.setPosition(dto.getPosition());
        entity.setDepartment(dto.getDepartment());
        entity.setRole(dto.getRole());
        entity.setReportingTo(dto.getReportingTo());
        entity.setEmpBasic(dto.getEmpBasic());
        entity.setEmpHra(dto.getEmpHra());
        entity.setEmpAllowance(dto.getEmpAllowance());
        entity.setEmpMonthGross(dto.getEmpMonthGross());
        entity.setEmpCtc(dto.getEmpCtc());
        entity.setEmpTds(dto.getEmpTds());
        entity.setEmpPt(dto.getEmpPt());
        entity.setEmpLoans(dto.getEmpLoans());
        entity.setActiveStatus(dto.getActiveStatus() != null ? Integer.parseInt(dto.getActiveStatus()) : 1);
        entity.setStatus(dto.getStatus() != null ? Integer.parseInt(dto.getStatus()) : 1);
        return entity;
    }

    // Entity → DTO
    public static EmpPositionDTO toDTO(EmpPositionEntity entity) {
        if (entity == null) return null;

        EmpPositionDTO dto = new EmpPositionDTO();
        dto.setId(entity.getId());
        dto.setEmpId(entity.getEmpId());
        dto.setEpDate(entity.getEpDate());
        dto.setEpEfficientDate(entity.getEpEfficientDate());
        dto.setPosition(entity.getPosition());
        dto.setDepartment(entity.getDepartment());
        dto.setRole(entity.getRole());
        dto.setReportingTo(entity.getReportingTo());
        dto.setEmpBasic(entity.getEmpBasic());
        dto.setEmpHra(entity.getEmpHra());
        dto.setEmpAllowance(entity.getEmpAllowance());
        dto.setEmpMonthGross(entity.getEmpMonthGross());
        dto.setEmpCtc(entity.getEmpCtc());
        dto.setEmpTds(entity.getEmpTds());
        dto.setEmpPt(entity.getEmpPt());
        dto.setEmpLoans(entity.getEmpLoans());
        dto.setActiveStatus(entity.getActiveStatus() != null ? String.valueOf(entity.getActiveStatus()) : null);
        dto.setStatus(entity.getStatus() != null ? String.valueOf(entity.getStatus()) : null);
        return dto;
    }
}