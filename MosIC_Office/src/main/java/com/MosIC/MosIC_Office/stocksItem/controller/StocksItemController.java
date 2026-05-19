package com.MosIC.MosIC_Office.stocksItem.controller;

import com.MosIC.MosIC_Office.stocksItem.dto.StockItemDTO;
import com.MosIC.MosIC_Office.stocksItem.service.StockItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-items")
@RequiredArgsConstructor
public class StocksItemController {

    private final StockItemService stockItemService;

    // POST /api/stock-items
    @PostMapping
    public ResponseEntity<StockItemDTO> createStockItem(@RequestBody StockItemDTO stockItemDTO) {
        StockItemDTO created = stockItemService.createStockItem(stockItemDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET /api/stock-items/{id}
    @GetMapping("/{id}")
    public ResponseEntity<StockItemDTO> getStockItemById(@PathVariable Long id) {
        StockItemDTO stockItemDTO = stockItemService.getStockItemById(id);
        return ResponseEntity.ok(stockItemDTO);
    }

    // GET /api/stock-items
    @GetMapping
    public ResponseEntity<List<StockItemDTO>> getAllStockItems() {
        List<StockItemDTO> list = stockItemService.getAllStockItems();
        return ResponseEntity.ok(list);
    }

    // GET /api/stock-items/status/{status}
    @GetMapping("/status/{status}")
    public ResponseEntity<List<StockItemDTO>> getStockItemsByStatus(@PathVariable Integer status) {
        List<StockItemDTO> list = stockItemService.getStockItemsByStatus(status);
        return ResponseEntity.ok(list);
    }

    // GET /api/stock-items/search?name=xyz
    @GetMapping("/search")
    public ResponseEntity<List<StockItemDTO>> searchStockItemsByName(@RequestParam String name) {
        List<StockItemDTO> list = stockItemService.searchStockItemsByName(name);
        return ResponseEntity.ok(list);
    }

    // PUT /api/stock-items/{id}
    @PutMapping("/{id}")
    public ResponseEntity<StockItemDTO> updateStockItem(
            @PathVariable Long id,
            @RequestBody StockItemDTO stockItemDTO) {
        StockItemDTO updated = stockItemService.updateStockItem(id, stockItemDTO);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/stock-items/{id}  (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStockItem(@PathVariable Long id) {
        stockItemService.deleteStockItem(id);
        return ResponseEntity.ok("Stock item with id " + id + " deleted successfully.");
    }
}