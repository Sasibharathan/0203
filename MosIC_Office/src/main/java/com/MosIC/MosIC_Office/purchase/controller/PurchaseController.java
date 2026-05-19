package com.MosIC.MosIC_Office.purchase.controller;

import com.MosIC.MosIC_Office.purchase.dto.PurchaseDTO;
import com.MosIC.MosIC_Office.purchase.service.PurchaseServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PurchaseController {

    private final PurchaseServices purchaseServices;

    // POST /api/purchases
    @PostMapping
    public ResponseEntity<PurchaseDTO> createPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        PurchaseDTO created = purchaseServices.createPurchase(purchaseDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET /api/purchases/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDTO> getPurchaseById(@PathVariable Long id) {
        PurchaseDTO purchase = purchaseServices.getPurchaseById(id);
        return ResponseEntity.ok(purchase);
    }

    // GET /api/purchases
    @GetMapping
    public ResponseEntity<List<PurchaseDTO>> getAllPurchases() {
        List<PurchaseDTO> purchases = purchaseServices.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }

    // PUT /api/purchases/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseDTO> updatePurchase(
            @PathVariable Long id,
            @RequestBody PurchaseDTO purchaseDTO) {
        PurchaseDTO updated = purchaseServices.updatePurchase(id, purchaseDTO);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/purchases/{id}  (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchase(@PathVariable Long id) {
        purchaseServices.deletePurchase(id);
        return ResponseEntity.ok("Purchase deleted successfully");
    }
}