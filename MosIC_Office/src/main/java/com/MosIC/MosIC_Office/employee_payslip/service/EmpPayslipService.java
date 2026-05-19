package com.MosIC.MosIC_Office.employee_payslip.service;

import java.util.List;

import com.MosIC.MosIC_Office.employee_payslip.dto.EmpPayslipDTO;

public interface EmpPayslipService {
  EmpPayslipDTO createEmpPayslip(EmpPayslipDTO empPayslipDTO);

  EmpPayslipDTO getEmpPayslipById(Long id);

  List<EmpPayslipDTO> getAllEmpPayslips();

  /**
   * NEW METHOD: Get all payslips for a specific employee
   * Required by frontend to show payslips for a particular employee
   */
  List<EmpPayslipDTO> getPayslipsByEmpId(Long empId);

  EmpPayslipDTO updateEmpPayslip(Long id, EmpPayslipDTO empPayslipDTO);

  void deleteEmpPayslip(Long id);

}