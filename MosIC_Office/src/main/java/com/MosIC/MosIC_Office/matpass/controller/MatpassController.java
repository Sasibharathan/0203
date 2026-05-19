package com.MosIC.MosIC_Office.matpass.controller;

import com.MosIC.MosIC_Office.matpass.dto.MatpassDTO;
import com.MosIC.MosIC_Office.matpass.service.MatpassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matpass")
@RequiredArgsConstructor
public class MatpassController {

    private final MatpassService matpassService;

    // POST /api/matpass
    @PostMapping
    public ResponseEntity<MatpassDTO> createMatpass(@RequestBody MatpassDTO matpassDTO) {
        MatpassDTO created = matpassService.createMatpass(matpassDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET /api/matpass/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MatpassDTO> getMatpassById(@PathVariable Long id) {
        MatpassDTO matpassDTO = matpassService.getMatpassById(id);
        return ResponseEntity.ok(matpassDTO);
    }

    // GET /api/matpass
    @GetMapping
    public ResponseEntity<List<MatpassDTO>> getAllMatpasses() {
        List<MatpassDTO> list = matpassService.getAllMatpasses();
        return ResponseEntity.ok(list);
    }

    // GET /api/matpass/status/{status}
    @GetMapping("/status/{status}")
    public ResponseEntity<List<MatpassDTO>> getMatpassesByStatus(@PathVariable Integer status) {
        List<MatpassDTO> list = matpassService.getMatpassesByStatus(status);
        return ResponseEntity.ok(list);
    }

    // GET /api/matpass/direction/{inOrOut}  (e.g. IN or OUT)
    @GetMapping("/direction/{inOrOut}")
    public ResponseEntity<List<MatpassDTO>> getMatpassesByInOrOut(@PathVariable String inOrOut) {
        List<MatpassDTO> list = matpassService.getMatpassesByInOrOut(inOrOut);
        return ResponseEntity.ok(list);
    }

    // PUT /api/matpass/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MatpassDTO> updateMatpass(
            @PathVariable Long id,
            @RequestBody MatpassDTO matpassDTO) {
        MatpassDTO updated = matpassService.updateMatpass(id, matpassDTO);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/matpass/{id}  (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatpass(@PathVariable Long id) {
        matpassService.deleteMatpass(id);
        return ResponseEntity.ok("Matpass with id " + id + " deleted successfully.");
    }
}