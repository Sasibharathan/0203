package com.MosIC.MosIC_Office.purchase_items.controller;

import com.MosIC.MosIC_Office.purchase_items.dto.PurchaseItemDTO;
import com.MosIC.MosIC_Office.purchase_items.service.PurchaseItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-items")
@AllArgsConstructor
//@CrossOrigin(origins = "*")
public class PurchaseItemController {

    private final PurchaseItemService purchaseItemService;

    // POST /api/purchase-items
    // Create a new purchase item
    @PostMapping
    public ResponseEntity<PurchaseItemDTO> createPurchaseItem(@RequestBody PurchaseItemDTO purchaseItemDTO) {
        PurchaseItemDTO created = purchaseItemService.createPurchaseItem(purchaseItemDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET /api/purchase-items/{id}
    // Get a single Purchase item by its own ID
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseItemDTO> getPurchaseItemById(@PathVariable Long id) {
        PurchaseItemDTO item = purchaseItemService.getPurchaseItemById(id);
        return ResponseEntity.ok(item);
    }

    // GET /api/purchase-items
    @GetMapping
    public ResponseEntity<List<PurchaseItemDTO>> getAllPurchaseItems() {
        List<PurchaseItemDTO> items = purchaseItemService.getAllPurchaseItems();
        return ResponseEntity.ok(items);
    }

    // GET /api/purchase-items/by-ref/{refFileNo}
    // Fetches all items linked to a specific Purchase document
    @GetMapping("/by-ref/{refFileNo}")
    public ResponseEntity<List<PurchaseItemDTO>> getItemsByRefFileNo(@PathVariable Integer refFileNo) {
        List<PurchaseItemDTO> items = purchaseItemService.getPurchaseItemsByRefFileNo(refFileNo);
        return ResponseEntity.ok(items);
    }

    // PUT /api/purchase-items/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseItemDTO> updatePurchaseItem(
            @PathVariable Long id,
            @RequestBody PurchaseItemDTO purchaseItemDTO) {
        PurchaseItemDTO updated = purchaseItemService.updatePurchaseItem(id, purchaseItemDTO);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/purchase-items/{id}  (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchaseItem(@PathVariable Long id) {
        purchaseItemService.deletePurchaseItem(id);
        return ResponseEntity.ok("Purchase item deleted successfully");
    }
}