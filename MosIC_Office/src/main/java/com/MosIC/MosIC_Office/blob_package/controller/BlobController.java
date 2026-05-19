package com.MosIC.MosIC_Office.blob_package.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.MosIC.MosIC_Office.blob_package.dto.BlobDTO;
import com.MosIC.MosIC_Office.blob_package.entity.BlobEntity;
import com.MosIC.MosIC_Office.blob_package.service.BlobService;

@RestController
@RequestMapping("/api/blobs")
public class BlobController {

  private final BlobService blobService;

  public BlobController(BlobService blobService) {
    this.blobService = blobService;
  }

  //get /api/blobs/{id}
  @GetMapping("/{id}")
  public ResponseEntity<BlobDTO> getBlobMeta(@PathVariable("id") Long id) {
    return ResponseEntity.ok(blobService.getBlobMetadataById(id));
  }

  // GET /api/blobs/{id}/download  → forces file download
  @GetMapping("/{id}/download")
  public ResponseEntity<byte[]> downloadBlob(@PathVariable("id") Long id) {
    BlobEntity blob = blobService.getBlobById(id);
    String contentType = resolveContentType(blob.getFileType());
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + blob.getFileName() + "\"")
        .body(blob.getFileData());
  }

  //GET /api/blobs/{id}/view  → opens inline in browser (PDF/image preview)
  @GetMapping("/{id}/view")
  public ResponseEntity<byte[]> viewBlob(@PathVariable("id") Long id) {
      BlobEntity blob = blobService.getBlobById(id);
      return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType(resolveContentType(blob.getFileType())))
          .header(HttpHeaders.CONTENT_DISPOSITION,
                  "inline; filename=\"" + blob.getFileName() + "\"")
          .body(blob.getFileData());
  }

  // POST /api/blobs — was MISSING, caused the 404
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<BlobDTO> createBlob(
      @RequestParam("file") MultipartFile file,
      @RequestParam(value = "description", required = false) String description) {
    BlobDTO created = blobService.createBlob(description, file);
    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  // GET /api/blobs — all blobs metadata
  @GetMapping
  public ResponseEntity<List<BlobDTO>> getAllBlobs() {
    return ResponseEntity.ok(blobService.getAllBlobs());
  }

  // DELETE /api/blobs/{id}
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBlob(@PathVariable("id") Long id) {
    blobService.deleteBlob(id);
    return ResponseEntity.noContent().build();
  }

  private String resolveContentType(String fileType) {
    if (fileType == null || fileType.isBlank()) {
      return MediaType.APPLICATION_OCTET_STREAM_VALUE;
    }
    return fileType;
  }
}
