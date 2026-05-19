package com.MosIC.MosIC_Office.activity_package.mapper;

import com.MosIC.MosIC_Office.activity_package.dto.ActivityDTO;
import com.MosIC.MosIC_Office.activity_package.entity.ActivityEntity;

public class ActivityMapper {

  public static ActivityDTO mapToDTO(ActivityEntity activityEntity) {
    return new ActivityDTO(
      activityEntity.getId(),
      activityEntity.getActivityReferenceNo(),
      activityEntity.getActivityDate(),
      activityEntity.getActivityRemarks(),
      activityEntity.getActivityDocId(),
      activityEntity.getActivityDocTable(),
      activityEntity.getActivityStatus()
    );
  }

  public static ActivityEntity mapToEntity(ActivityDTO activityDTO) {
    return new ActivityEntity(
      activityDTO.getId(),
      activityDTO.getActivityReferenceNo(),
      activityDTO.getActivityDate(),
      activityDTO.getActivityRemarks(),
      activityDTO.getActivityDocId(),
      activityDTO.getActivityDocTable(),
      activityDTO.getActivityStatus()
    );
  }

}
