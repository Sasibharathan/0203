package com.MosIC.MosIC_Office.employee_payslip.mapper;

import com.MosIC.MosIC_Office.employee_payslip.dto.EmpPayslipDTO;
import com.MosIC.MosIC_Office.employee_payslip.entity.EmpPayslipEntity;

public class EmpPayslipMapper {

    //      These calls previously failed because EmpPayslipEntity had no
    //      getters/setters. Now that @Getter/@Setter are on the entity,
    //      all entity.getX() and entity.setX() calls compile correctly.

    public static EmpPayslipDTO toDTO(EmpPayslipEntity entity) {
        return new EmpPayslipDTO(
            entity.getId(),
            entity.getEmpId(),
            entity.getEmpMonth(),
            entity.getEmpYear(),
            entity.getBasic(),
            entity.getHra(),
            entity.getAllowancess(),   // matches field name "allowancess"
            entity.getTotalGross(),
            entity.getTds(),
            entity.getPt(),
            entity.getLoan(),
            entity.getTotalDeduction(),
            entity.getStatus()
        );
    }

    public static EmpPayslipEntity toEntity(EmpPayslipDTO dto) {
        EmpPayslipEntity entity = new EmpPayslipEntity();
        entity.setId(dto.getId());
        entity.setEmpId(dto.getEmpId());
        entity.setEmpMonth(dto.getEmpMonth());
        entity.setEmpYear(dto.getEmpYear());
        entity.setBasic(dto.getBasic());
        entity.setHra(dto.getHra());
        entity.setAllowancess(dto.getAllowancess());   // matches field name "allowancess"
        entity.setTotalGross(dto.getTotalGross());
        entity.setTds(dto.getTds());
        entity.setPt(dto.getPt());
        entity.setLoan(dto.getLoan());
        entity.setTotalDeduction(dto.getTotalDeduction());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}