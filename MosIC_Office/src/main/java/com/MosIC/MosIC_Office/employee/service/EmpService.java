package com.MosIC.MosIC_Office.employee.service;

import com.MosIC.MosIC_Office.employee.dto.EmpDTO;
import java.util.List;

public interface EmpService {
    EmpDTO createEmp(EmpDTO empDTO);
    EmpDTO getEmpById(Long id);
    List<EmpDTO> getAllEmps();
    EmpDTO updateEmp(Long id, EmpDTO empDTO);
    void deleteEmp(Long id);
}