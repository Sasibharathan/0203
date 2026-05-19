package com.MosIC.MosIC_Office.sales_items.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MosIC.MosIC_Office.sales_items.dto.SalesItemDTO;
import com.MosIC.MosIC_Office.sales_items.service.SalesItemService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/sales-items")
@AllArgsConstructor
public class SalesItemController {

    private final SalesItemService salesItemService;

    // ─── POST /api/sales-items ─────────────────────────────────────────────────
    // Create a new sales item
    @PostMapping
    public ResponseEntity<SalesItemDTO> createSalesItem(@RequestBody SalesItemDTO salesItemDTO) {
        SalesItemDTO created = salesItemService.createSalesItem(salesItemDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ─── GET /api/sales-items/{id} ─────────────────────────────────────────────
    // Get a single sales item by its own ID
    @GetMapping("/{id}")
    public ResponseEntity<SalesItemDTO> getSalesItemById(@PathVariable Long id) {
        SalesItemDTO salesItem = salesItemService.getSalesItemById(id);
        return ResponseEntity.ok(salesItem);
    }

    // ─── GET /api/sales-items/by-sales/{refFileNo} ─────────────────────────────
    // Get ALL items belonging to a specific Sales document (by Sales ID)
    @GetMapping("/by-sales/{refFileNo}")
    public ResponseEntity<List<SalesItemDTO>> getSalesItemsByRefFileNo(@PathVariable Integer refFileNo) {
        List<SalesItemDTO> items = salesItemService.getSalesItemsByRefFileNo(refFileNo);
        return ResponseEntity.ok(items);
    }

    // ─── GET /api/sales-items ──────────────────────────────────────────────────
    // Get all sales items (across all documents)
    @GetMapping
    public ResponseEntity<List<SalesItemDTO>> getAllSalesItems() {
        List<SalesItemDTO> items = salesItemService.getAllSalesItems();
        return ResponseEntity.ok(items);
    }

    // ─── PUT /api/sales-items/{id} ─────────────────────────────────────────────
    // Update a sales item by its ID
    @PutMapping("/{id}")
    public ResponseEntity<SalesItemDTO> updateSalesItem(
            @PathVariable Long id,
            @RequestBody SalesItemDTO salesItemDTO) {
        SalesItemDTO updated = salesItemService.updateSalesItem(id, salesItemDTO);
        return ResponseEntity.ok(updated);
    }

    // ─── DELETE /api/sales-items/{id} ─────────────────────────────────────────
    // Delete a single sales item by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSalesItem(@PathVariable Long id) {
        salesItemService.deleteSalesItem(id);
        return ResponseEntity.ok("Sales item deleted successfully.");
    }

    // ─── DELETE /api/sales-items/by-sales/{refFileNo} ─────────────────────────
    // Delete ALL items belonging to a Sales document (used when deleting a Sales doc)
    @DeleteMapping("/by-sales/{refFileNo}")
    public ResponseEntity<String> deleteSalesItemsByRefFileNo(@PathVariable Integer refFileNo) {
        salesItemService.deleteSalesItemsByRefFileNo(refFileNo);
        return ResponseEntity.ok("All items for sales document " + refFileNo + " deleted successfully.");
    }

}