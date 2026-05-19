package com.MosIC.MosIC_Office.blob_package.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BlobDTO {

  private Long id;
  private String fileName;
  private Long fileSize;
  private String fileType;
  private byte[] fileData;
  private String description;
  private Integer status;

}
