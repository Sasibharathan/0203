package com.MosIC.MosIC_Office.blob_package.mapper;

import com.MosIC.MosIC_Office.blob_package.dto.BlobDTO;
import com.MosIC.MosIC_Office.blob_package.entity.BlobEntity;

public class BlobMapper {

  public static BlobDTO mapToDTO(BlobEntity blobEntity) {
    return new BlobDTO(
        blobEntity.getId(),
        blobEntity.getFileName(),
        blobEntity.getFileSize(),
        blobEntity.getFileType(),
        blobEntity.getFileData(),
        blobEntity.getDescription(),
        blobEntity.getStatus());
  }

}
