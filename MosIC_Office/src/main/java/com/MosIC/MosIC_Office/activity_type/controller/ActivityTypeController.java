package com.MosIC.MosIC_Office.activity_type.controller;

import com.MosIC.MosIC_Office.activity_type.dto.ActivityTypeDTO;
import com.MosIC.MosIC_Office.activity_type.service.ActivityTypeServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/activity-type")
public class ActivityTypeController {

    private final ActivityTypeServices activityTypeServices;

    public ActivityTypeController(ActivityTypeServices activityTypeServices) {
        this.activityTypeServices = activityTypeServices;
    }

    // ── CREATE ────────────────────────────────────────────────────────────────
    // POST /api/activity-type
    @PostMapping
    public ResponseEntity<ActivityTypeDTO> createActivityType(
            @RequestBody ActivityTypeDTO activityTypeDTO) {
        ActivityTypeDTO created = activityTypeServices.createActivityType(activityTypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ── GET ALL ───────────────────────────────────────────────────────────────
    // GET /api/activity-type
    @GetMapping
    public ResponseEntity<List<ActivityTypeDTO>> getAllActivityTypes() {
        return ResponseEntity.ok(activityTypeServices.getAllActivityTypes());
    }

    // ── GET BY ID ─────────────────────────────────────────────────────────────
    // GET /api/activity-type/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ActivityTypeDTO> getActivityTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(activityTypeServices.getActivityTypeById(id));
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    // PUT /api/activity-type/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ActivityTypeDTO> updateActivityType(
            @PathVariable Long id,
            @RequestBody ActivityTypeDTO activityTypeDTO) {
        ActivityTypeDTO updated = activityTypeServices.updateActivityType(id, activityTypeDTO);
        return ResponseEntity.ok(updated);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    // DELETE /api/activity-type/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteActivityType(@PathVariable Long id) {
        activityTypeServices.deleteActivityType(id);
        return ResponseEntity.ok(Map.of("message", "Activity type deleted successfully"));
    }
}