package com.MosIC.MosIC_Office.employee_position.controller;

import com.MosIC.MosIC_Office.employee_position.dto.EmpPositionDTO;
import com.MosIC.MosIC_Office.employee_position.service.EmpPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emp-position")
@RequiredArgsConstructor
public class EmpPositionController {

    private final EmpPositionService empPositionService;

    // POST /api/emp-position
    @PostMapping
    public ResponseEntity<EmpPositionDTO> createEmpPosition(@RequestBody EmpPositionDTO dto) {
        EmpPositionDTO created = empPositionService.createEmpPosition(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET /api/emp-position/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EmpPositionDTO> getEmpPositionById(@PathVariable Long id) {
        EmpPositionDTO dto = empPositionService.getEmpPositionById(id);
        return ResponseEntity.ok(dto);
    }

    // GET /api/emp-position
    @GetMapping
    public ResponseEntity<List<EmpPositionDTO>> getAllEmpPositions() {
        List<EmpPositionDTO> list = empPositionService.getAllEmpPositions();
        return ResponseEntity.ok(list);
    }

    // GET /api/emp-position/employee/{empId}
    @GetMapping("/employee/{empId}")
    public ResponseEntity<List<EmpPositionDTO>> getEmpPositionsByEmpId(@PathVariable Long empId) {
        List<EmpPositionDTO> list = empPositionService.getEmpPositionsByEmpId(empId);
        return ResponseEntity.ok(list);
    }

    // PUT /api/emp-position/{id}
    @PutMapping("/{id}")
    public ResponseEntity<EmpPositionDTO> updateEmpPosition(
            @PathVariable Long id,
            @RequestBody EmpPositionDTO dto) {
        EmpPositionDTO updated = empPositionService.updateEmpPosition(id, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/emp-position/{id}  (hard delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmpPosition(@PathVariable Long id) {
        empPositionService.deleteEmpPosition(id);
        return ResponseEntity.ok("EmpPosition deleted successfully.");
    }

    // PATCH /api/emp-position/{id}/deactivate  (soft delete)
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> softDeleteEmpPosition(@PathVariable Long id) {
        empPositionService.softDeleteEmpPosition(id);
        return ResponseEntity.ok("EmpPosition deactivated successfully.");
    }

    // PATCH /api/emp-position/{id}/reactivate  (undo soft delete)
    @PatchMapping("/{id}/reactivate")
    public ResponseEntity<String> reactivateEmpPosition(@PathVariable Long id) {
        empPositionService.reactivateEmpPosition(id);
        return ResponseEntity.ok("EmpPosition reactivated successfully.");
    }
}