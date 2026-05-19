package com.MosIC.MosIC_Office.sales.controller;

import com.MosIC.MosIC_Office.sales.dto.SalesDTO;
import com.MosIC.MosIC_Office.sales.service.SalesServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private final SalesServices salesServices;

    public SalesController(SalesServices salesServices) {
        this.salesServices = salesServices;
    }

    // ── CREATE ────────────────────────────────────────────────────────────────
    // POST /api/sales
    @PostMapping
    public ResponseEntity<SalesDTO> createSales(@RequestBody SalesDTO salesDTO) {
        SalesDTO created = salesServices.createSales(salesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ── GET ALL ───────────────────────────────────────────────────────────────
    // GET /api/sales
    @GetMapping
    public ResponseEntity<List<SalesDTO>> getAllSales() {
        return ResponseEntity.ok(salesServices.getAllSales());
    }

    // ── GET BY ID ─────────────────────────────────────────────────────────────
    // GET /api/sales/{id}
    @GetMapping("/{id}")
    public ResponseEntity<SalesDTO> getSalesById(@PathVariable Long id) {
        return ResponseEntity.ok(salesServices.getSalesById(id));
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    // PUT /api/sales/{id}
    @PutMapping("/{id}")
    public ResponseEntity<SalesDTO> updateSales(
            @PathVariable Long id,
            @RequestBody SalesDTO salesDTO) {
        return ResponseEntity.ok(salesServices.updateSales(id, salesDTO));
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    // DELETE /api/sales/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteSales(@PathVariable Long id) {
        salesServices.deleteSales(id);
        return ResponseEntity.ok(Map.of("message", "Sales record deleted successfully"));
    }
}
