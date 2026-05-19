package com.MosIC.MosIC_Office.stocks.controller;

import com.MosIC.MosIC_Office.stocks.dto.StocksDTO;
import com.MosIC.MosIC_Office.stocks.service.StocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StocksController {

    private final StocksService stocksService;

    // POST /api/stocks
    @PostMapping
    public ResponseEntity<StocksDTO> createStock(@RequestBody StocksDTO stocksDTO) {
        StocksDTO created = stocksService.createStock(stocksDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET /api/stocks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<StocksDTO> getStockById(@PathVariable Long id) {
        StocksDTO stocksDTO = stocksService.getStockById(id);
        return ResponseEntity.ok(stocksDTO);
    }

    // GET /api/stocks
    @GetMapping
    public ResponseEntity<List<StocksDTO>> getAllStocks() {
        List<StocksDTO> list = stocksService.getAllStocks();
        return ResponseEntity.ok(list);
    }

    // GET /api/stocks/status/{status}
    @GetMapping("/status/{status}")
    public ResponseEntity<List<StocksDTO>> getStocksByStatus(@PathVariable Integer status) {
        List<StocksDTO> list = stocksService.getStocksByStatus(status);
        return ResponseEntity.ok(list);
    }

    // GET /api/stocks/item/{stockItemId}
    @GetMapping("/item/{stockItemId}")
    public ResponseEntity<List<StocksDTO>> getStocksByStockItemId(@PathVariable Long stockItemId) {
        List<StocksDTO> list = stocksService.getStocksByStockItemId(stockItemId);
        return ResponseEntity.ok(list);
    }

    // GET /api/stocks/matpass/{matPassId}
    @GetMapping("/matpass/{matPassId}")
    public ResponseEntity<List<StocksDTO>> getStocksByMatPassId(@PathVariable Long matPassId) {
        List<StocksDTO> list = stocksService.getStocksByMatPassId(matPassId);
        return ResponseEntity.ok(list);
    }

    // GET /api/stocks/direction/{inOrOut}  (e.g. IN or OUT)
    @GetMapping("/direction/{inOrOut}")
    public ResponseEntity<List<StocksDTO>> getStocksByInOut(@PathVariable String inOrOut) {
        List<StocksDTO> list = stocksService.getStocksByInOut(inOrOut);
        return ResponseEntity.ok(list);
    }

    // PUT /api/stocks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<StocksDTO> updateStock(
            @PathVariable Long id,
            @RequestBody StocksDTO stocksDTO) {
        StocksDTO updated = stocksService.updateStock(id, stocksDTO);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/stocks/{id}  (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id) {
        stocksService.deleteStock(id);
        return ResponseEntity.ok("Stock with id " + id + " deleted successfully.");
    }
}