package com.MosIC.MosIC_Office.activity_type.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter           // ← generates getActivityTypeName(), getActivityTypeStatus()
@Setter
@AllArgsConstructor  // ← generates constructor(id, name, status)
@NoArgsConstructor
public class ActivityTypeDTO {
  private Long id;// wrapper, allows NOT null
  private String activityTypeName;
  private String activityTypeStatus;
}
