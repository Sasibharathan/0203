package com.MosIC.MosIC_Office.file_package.mapper;

import com.MosIC.MosIC_Office.file_package.dto.FileDTO;
import com.MosIC.MosIC_Office.file_package.entity.FileEntity;

public class FileMapper {

  public static FileDTO mapToDTO(FileEntity fileEntity) {
   return new FileDTO(
     fileEntity.getId(),
     fileEntity.getFileDate(),
     fileEntity.getFileActivity(),
     fileEntity.getFileSubject(),
     fileEntity.getFileDescription(),
     fileEntity.getFileStatus()//,
     //fileEntity.getDateCreated()
   );
  }
  public static FileEntity mapToEntity(FileDTO fileDTO) {
    return new FileEntity(
      fileDTO.getId(),
      fileDTO.getFileDate(),
      fileDTO.getFileActivity(),
      fileDTO.getFileSubject(),
      fileDTO.getFileDescription(),
      fileDTO.getFileStatus()//,
      //null // dateCreated will be handled by DB
    );
  }
}
