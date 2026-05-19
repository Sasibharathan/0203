package com.MosIC.MosIC_Office.customers_party.controller;

import com.MosIC.MosIC_Office.customers_party.dto.PartyDTO;
import com.MosIC.MosIC_Office.customers_party.service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PartyController {

    private final PartyService partyService;

    // ── Nested: GET all party contacts for a customer (company/org) ───────────
    // GET /api/customers/{customerId}/parties
    @GetMapping("/api/customers/{customerId}/parties")
    public ResponseEntity<List<PartyDTO>> getPartiesByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(partyService.getPartiesByCustomerId(customerId));
    }

    // ── Standalone CRUD ───────────────────────────────────────────────────────

    // POST /api/parties  — body must include customerId
    @PostMapping("/api/parties")
    public ResponseEntity<PartyDTO> createParty(@RequestBody PartyDTO partyDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partyService.createParty(partyDTO));
    }

    // GET /api/parties
    @GetMapping("/api/parties")
    public ResponseEntity<List<PartyDTO>> getAllParties() {
        return ResponseEntity.ok(partyService.getAllParties());
    }

    // GET /api/parties/{id}
    @GetMapping("/api/parties/{id}")
    public ResponseEntity<PartyDTO> getPartyById(@PathVariable Long id) {
        return ResponseEntity.ok(partyService.getPartyById(id));
    }

    // PUT /api/parties/{id}
    @PutMapping("/api/parties/{id}")
    public ResponseEntity<PartyDTO> updateParty(@PathVariable Long id,
                                                @RequestBody PartyDTO partyDTO) {
        return ResponseEntity.ok(partyService.updateParty(id, partyDTO));
    }

    // DELETE /api/parties/{id}
    @DeleteMapping("/api/parties/{id}")
    public ResponseEntity<Void> deleteParty(@PathVariable Long id) {
        partyService.deleteParty(id);
        return ResponseEntity.noContent().build();
    }
}