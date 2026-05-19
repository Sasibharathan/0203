package com.MosIC.MosIC_Office.blob_package.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MosIC.MosIC_Office.blob_package.entity.BlobEntity;

public interface BlobRepository extends JpaRepository<BlobEntity, Long> {

 /// List<BlobEntity> findByVoucherId(String voucherId);

}
