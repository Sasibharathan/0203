package com.MosIC.MosIC_Office.blob_package.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.MosIC.MosIC_Office.blob_package.dto.BlobDTO;
import com.MosIC.MosIC_Office.blob_package.entity.BlobEntity;

public interface BlobService {

  BlobDTO createBlob(String voucherId, MultipartFile file);

  BlobEntity getBlobById(Long id);

  BlobDTO getBlobMetadataById(Long id);

  //List<BlobDTO> getBlobMetadataByVoucherId(String voucherId);

  List<BlobDTO> getAllBlobs();

  BlobDTO updateBlobMetadata(Long id, BlobDTO blobDTO);

  void deleteBlob(Long id);

}
