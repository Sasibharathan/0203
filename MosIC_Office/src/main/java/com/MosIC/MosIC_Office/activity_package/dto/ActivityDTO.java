package com.MosIC.MosIC_Office.activity_package.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ActivityDTO {
  private Long id;//// wrapper, allows NOT null
  private String activityReferenceNo;
  private String activityDate;
  private String activityRemarks;
  private String activityDocId;
  private String activityDocTable;
  private String activityStatus;

}
