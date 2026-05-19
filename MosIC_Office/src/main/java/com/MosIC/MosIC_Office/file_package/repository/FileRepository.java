package com.MosIC.MosIC_Office.file_package.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.MosIC.MosIC_Office.file_package.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
