package com.MosIC.MosIC_Office.employee_payslip.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MosIC.MosIC_Office.employee_payslip.dto.EmpPayslipDTO;
import com.MosIC.MosIC_Office.employee_payslip.service.EmpPayslipService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payslips")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // adjust origin for your React frontend URL (e.g., "http://localhost:3000")
public class EmpPayslipController {

    private final EmpPayslipService empPayslipService;

    // POST /api/payslips
    @PostMapping
    public ResponseEntity<EmpPayslipDTO> createPayslip(@RequestBody EmpPayslipDTO empPayslipDTO) {
        EmpPayslipDTO created = empPayslipService.createEmpPayslip(empPayslipDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET /api/payslips/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EmpPayslipDTO> getPayslipById(@PathVariable Long id) {
        EmpPayslipDTO payslip = empPayslipService.getEmpPayslipById(id);
        return ResponseEntity.ok(payslip);
    }

    // GET /api/payslips
    @GetMapping
    public ResponseEntity<List<EmpPayslipDTO>> getAllPayslips() {
        List<EmpPayslipDTO> payslips = empPayslipService.getAllEmpPayslips();
        return ResponseEntity.ok(payslips);
    }

    /**
     * NEW ENDPOINT: Get all payslips for a specific employee
     * GET /api/payslips/employee/{empId}
     * This endpoint allows frontend to fetch payslips for a particular employee
     */
    @GetMapping("/employee/{empId}")
    public ResponseEntity<List<EmpPayslipDTO>> getPayslipsByEmpId(@PathVariable Long empId) {
        List<EmpPayslipDTO> payslips = empPayslipService.getPayslipsByEmpId(empId);
        return ResponseEntity.ok(payslips);
    }

    // PUT /api/payslips/{id}
    @PutMapping("/{id}")
    public ResponseEntity<EmpPayslipDTO> updatePayslip(
            @PathVariable Long id,
            @RequestBody EmpPayslipDTO empPayslipDTO) {
        EmpPayslipDTO updated = empPayslipService.updateEmpPayslip(id, empPayslipDTO);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/payslips/{id}  (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayslip(@PathVariable Long id) {
        empPayslipService.deleteEmpPayslip(id);
        return ResponseEntity.ok("Payslip deactivated successfully.");
    }
}