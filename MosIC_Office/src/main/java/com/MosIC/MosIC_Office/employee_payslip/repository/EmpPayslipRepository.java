package com.MosIC.MosIC_Office.employee_payslip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MosIC.MosIC_Office.employee_payslip.entity.EmpPayslipEntity;

public interface EmpPayslipRepository extends JpaRepository<EmpPayslipEntity, Long> {

    // Fetch all payslips for a specific employee
    List<EmpPayslipEntity> findByEmpId(Long empId);

    // FIX: Added — used by getAllEmpPayslips() to return only active records
    List<EmpPayslipEntity> findAllByStatus(Integer status);

    // FIX: Added — used by getPayslipsByEmpId() to return only active records for one employee
    List<EmpPayslipEntity> findByEmpIdAndStatus(Long empId, Integer status);

    // Fetch payslips by employee, month and year (useful for duplicate checks)
    List<EmpPayslipEntity> findByEmpIdAndEmpMonthAndEmpYear(Long empId, String empMonth, String empYear);
}