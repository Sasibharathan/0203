package com.MosIC.MosIC_Office.activity_package.controller;

import java.util.List;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.MosIC.MosIC_Office.activity_package.dto.ActivityDTO;
import com.MosIC.MosIC_Office.activity_package.service.ActivityServices;
import com.MosIC.MosIC_Office.blob_package.entity.BlobEntity;
import com.MosIC.MosIC_Office.blob_package.repository.BlobRepository;

@RestController
@RequestMapping("/api/activities")
public class activityController {

  private final ActivityServices activityServices;
  private final BlobRepository docBlobRepository;

    // ✅ CONSTRUCTOR INJECTION (IMPORTANT)
    public activityController(ActivityServices activityServices, BlobRepository docBlobRepository) {
        this.docBlobRepository = docBlobRepository;
        this.activityServices = activityServices;
    }

    //build create activity rest api post /api/activities
    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO) {
        return ResponseEntity.ok(activityServices.createActivity(activityDTO));
    }

    //build get all activity by id rest api
   // @GetMapping("{id}")
   // public ResponseEntity<ActivityDTO> getActivityById(@PathVariable("id") Long id) {
   //   ActivityDTO activityDTO = activityServices.getActivityById(id);
    //  return ResponseEntity.ok(activityDTO);
   // }

    // Specific routes BEFORE generic /{id}
    // GET /api/activities/by-ref/FILE-001
    // Returns all activities that belong to the file with Ref_file_no = "FILE-001"
    @GetMapping("/by-ref/{refFileNo}")
    public ResponseEntity<List<ActivityDTO>> getByRefNo(@PathVariable String refFileNo) {
        return ResponseEntity.ok(activityServices.getActivitiesByRefNo(refFileNo));
    }

    // DELETE /api/activities/by-ref/FILE-001  → deletes ALL activities with that ref
    @DeleteMapping("/by-ref/{refFileNo}")
    public ResponseEntity<String> deleteByRefNo(@PathVariable("refFileNo") String refFileNo) {
        activityServices.deleteActivitiesByRefNo(refFileNo);
        return ResponseEntity.ok("All activities for ref '" + refFileNo + "' deleted successfully");
    }

    //build get all activities rest api
    @GetMapping
    public ResponseEntity<List<ActivityDTO>> getActivitiesByRefNo(@RequestParam("refFileNo") String refFileNo) {
      List<ActivityDTO> activities = activityServices.getActivitiesByRefNo(refFileNo);
      return ResponseEntity.ok(activities);
    }

    // GET /api/activities/{id}  ← generic, always LAST
    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(activityServices.getActivityById(id));
    }

    //build update activity rest api    // GET /api/activities/{id}  ← generic, must be LAST
    @PutMapping("{id}")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable("id") Long id, @RequestBody ActivityDTO activityDTO) {
      ActivityDTO updatedActivity = activityServices.updateActivity(id, activityDTO);
      return ResponseEntity.ok(updatedActivity);
    }

    //build delete activity rest api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable("id") Long id) {
      activityServices.deleteActivity(id);
      return ResponseEntity.ok("Activity deleted successfully");
    }


    // New endpoint
//   @GetMapping("{id}/file")
//   public ResponseEntity<byte[]> downloadActivityFile(@PathVariable("id") Long id) {
//     ActivityDTO activity = activityServices.getActivityById(id);

//     String docId = activity.getActivityDocId();
//     if (docId == null || docId.isBlank()) {
//         return ResponseEntity.notFound().build();
//     }

//     BlobEntity blob = docBlobRepository.findById(Long.parseLong(docId))
//         .orElseThrow(() -> new RuntimeException("BLOB not found with id: " + docId));

//     return ResponseEntity.ok()
//         .contentType(MediaType.parseMediaType(blob.getFileType()))
//         .header(HttpHeaders.CONTENT_DISPOSITION,
//                 "attachment; filename=\"" + blob.getFileName() + "\"")
//         .body(blob.getFileData());
// }


  }
