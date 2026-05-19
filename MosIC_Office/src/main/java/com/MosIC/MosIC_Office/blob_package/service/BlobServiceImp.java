package com.MosIC.MosIC_Office.blob_package.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.MosIC.MosIC_Office.blob_package.dto.BlobDTO;
import com.MosIC.MosIC_Office.blob_package.entity.BlobEntity;
import com.MosIC.MosIC_Office.blob_package.mapper.BlobMapper;
import com.MosIC.MosIC_Office.blob_package.repository.BlobRepository;

@Service
public class BlobServiceImp implements BlobService {

    private final BlobRepository blobRepository;

    // ── NEW: allowed file types ───────────────────────────────────────────────
    private static final Set<String> ALLOWED_TYPES = Set.of(
        "application/pdf",
        "image/jpeg",
        "image/png",
        "image/gif",
        "image/webp",
        "application/msword",
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
        "application/vnd.ms-excel",
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
        "text/plain"
    );

    // ── NEW: max file size 50 MB ──────────────────────────────────────────────
    private static final long MAX_FILE_SIZE = 50L * 1024 * 1024;

    public BlobServiceImp(BlobRepository blobRepository) {
        this.blobRepository = blobRepository;
    }

    @Override
    public BlobDTO createBlob(String description, MultipartFile file) {

        // your original check — kept exactly as is
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is required");
        }

        // ── NEW: file size check ──────────────────────────────────────────────
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException(
                "File too large. Maximum size is 10 MB. " +
                "Your file: " + (file.getSize() / (1024 * 1024)) + " MB."
            );
        }

        // ── NEW: file type check ──────────────────────────────────────────────
        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
            throw new IllegalArgumentException(
                "File type not allowed: " + contentType +
                ". Allowed: PDF, JPG, PNG, GIF, WEBP, DOC, DOCX, XLS, XLSX, TXT."
            );
        }

        // ── everything below is exactly your original code ────────────────────
        BlobEntity blobEntity = new BlobEntity();
        blobEntity.setFileName(resolveFileName(file));
        blobEntity.setFileSize(file.getSize());
        blobEntity.setFileType(file.getContentType());
        blobEntity.setDescription(description);

        try {
            blobEntity.setFileData(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read uploaded file", e);
        }

        BlobEntity saved    = blobRepository.save(blobEntity);
        BlobEntity reloaded = blobRepository.findById(saved.getId()).orElse(saved);
        return BlobMapper.mapToDTO(reloaded);
    }

    @Override
    public BlobEntity getBlobById(Long id) {
        return blobRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Blob not found with id: " + id));
    }

    @Override
    public BlobDTO getBlobMetadataById(Long id) {
        return BlobMapper.mapToDTO(getBlobById(id));
    }

    @Override
    public List<BlobDTO> getAllBlobs() {
        return blobRepository.findAll().stream()
            .map(BlobMapper::mapToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public BlobDTO updateBlobMetadata(Long id, BlobDTO blobDTO) {
        BlobEntity entity = getBlobById(id);
        entity.setDescription(blobDTO.getDescription());
        BlobEntity updated = blobRepository.save(entity);
        return BlobMapper.mapToDTO(updated);
    }

    public BlobDTO updateBlobDescription(Long id, String description) {
        BlobEntity entity = getBlobById(id);
        entity.setDescription(description);
        BlobEntity updated = blobRepository.save(entity);
        return BlobMapper.mapToDTO(updated);
    }

    @Override
    public void deleteBlob(Long id) {
        blobRepository.delete(getBlobById(id));
    }

    private String resolveFileName(MultipartFile file) {
        String name = file.getOriginalFilename();
        return (name == null || name.isBlank()) ? "uploaded-file" : name;
    }
}