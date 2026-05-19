package com.MosIC.MosIC_Office.file_package.dto;

import java.sql.Timestamp;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor



public class FileDTO {
  private Long id;//// ✅ wrapper, allows null
  private String fileDate;
  private String fileActivity;
  private String fileSubject;
  private String fileDescription;
  private Integer fileStatus;
 // private Timestamp dateCreated;
}
