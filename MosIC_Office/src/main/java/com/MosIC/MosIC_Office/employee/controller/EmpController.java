package com.MosIC.MosIC_Office.employee.controller;

import com.MosIC.MosIC_Office.employee.dto.EmpDTO;
import com.MosIC.MosIC_Office.employee.service.EmpService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmpController {

    private final EmpService empService;

    @PostMapping
    public ResponseEntity<EmpDTO> createEmp(@RequestBody EmpDTO empDTO) {
        return new ResponseEntity<>(empService.createEmp(empDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpDTO> getEmpById(@PathVariable Long id) {
        return ResponseEntity.ok(empService.getEmpById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmpDTO>> getAllEmps() {
        return ResponseEntity.ok(empService.getAllEmps());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpDTO> updateEmp(@PathVariable Long id, @RequestBody EmpDTO empDTO) {
        return ResponseEntity.ok(empService.updateEmp(id, empDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable Long id) {
        empService.deleteEmp(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}