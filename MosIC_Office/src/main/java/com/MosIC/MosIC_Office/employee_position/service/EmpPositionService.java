package com.MosIC.MosIC_Office.employee_position.service;

import com.MosIC.MosIC_Office.employee_position.dto.EmpPositionDTO;

import java.util.List;

public interface EmpPositionService {

    EmpPositionDTO createEmpPosition(EmpPositionDTO dto);

    EmpPositionDTO getEmpPositionById(Long id);

    List<EmpPositionDTO> getAllEmpPositions();

    List<EmpPositionDTO> getEmpPositionsByEmpId(Long empId);

    EmpPositionDTO updateEmpPosition(Long id, EmpPositionDTO dto);

    void deleteEmpPosition(Long id);

    void softDeleteEmpPosition(Long id);

    void reactivateEmpPosition(Long id);
}