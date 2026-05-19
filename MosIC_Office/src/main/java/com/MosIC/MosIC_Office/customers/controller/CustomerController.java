package com.MosIC.MosIC_Office.customers.controller;

import com.MosIC.MosIC_Office.customers.dto.CustomerDTO;
import com.MosIC.MosIC_Office.customers.service.CustomerServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerServices customerServices;

    public CustomerController(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    // ── CREATE ----------------------------------------------------------------
    // POST /api/customers
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO created = customerServices.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ── GET ALL ----------------------------------------------------------------
    // GET /api/customers
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerServices.getAllCustomers());
    }

    // ── GET BY ID ----------------------------------------------------------------
    // GET /api/customers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerServices.getCustomerById(id));
    }

    // ── UPDATE ----------------------------------------------------------------
    // PUT /api/customers/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerServices.updateCustomer(id, customerDTO));
    }

    // ── DELETE ----------------------------------------------------------------
    // DELETE /api/customers/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable Long id) {
        customerServices.deleteCustomer(id);
        return ResponseEntity.ok(Map.of("message", "Customer deleted successfully"));
    }
}
