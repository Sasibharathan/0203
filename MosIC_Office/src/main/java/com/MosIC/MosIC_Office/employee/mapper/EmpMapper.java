package com.MosIC.MosIC_Office.employee.mapper;

import com.MosIC.MosIC_Office.employee.dto.EmpDTO;
import com.MosIC.MosIC_Office.employee.entity.EmpEntity;

public class EmpMapper {

    public static EmpDTO mapToDTO(EmpEntity e) {
        return new EmpDTO(
            e.getId(),
            e.getEmpName(),
            e.getEmpLastName(),
            e.getEmpDob(),
            e.getEmpPh(),
            e.getEmpMail(),
            e.getEmpPan(),
            e.getEmpAdhar(),
            e.getEmpAccNo(),
            e.getEmpBankName(),
            e.getEmpAccName(),
            e.getEmpIfscCode(),
            e.getEmpAddress1(),
            e.getEmpAddress2(),
            e.getEmpAddress3(),
            e.getEmpDoj(),
            e.getStatus()
        );
    }

    public static EmpEntity mapToEntity(EmpDTO d) {
        return new EmpEntity(
            d.getId(),
            d.getEmpName(),
            d.getEmpLastName(),
            d.getEmpDob(),
            d.getEmpPh(),
            d.getEmpMail(),
            d.getEmpPan(),
            d.getEmpAdhar(),
            d.getEmpAccNo(),
            d.getEmpBankName(),
            d.getEmpAccName(),
            d.getEmpIfscCode(),
            d.getEmpAddress1(),
            d.getEmpAddress2(),
            d.getEmpAddress3(),
            d.getEmpDoj(),
            d.getStatus()
        );
    }
}