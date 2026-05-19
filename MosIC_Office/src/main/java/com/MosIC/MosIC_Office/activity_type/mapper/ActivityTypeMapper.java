package com.MosIC.MosIC_Office.activity_type.mapper;


import com.MosIC.MosIC_Office.activity_type.dto.ActivityTypeDTO;
import com.MosIC.MosIC_Office.activity_type.entity.ActivityTypeEntity;

public class ActivityTypeMapper {

  public static ActivityTypeDTO mapToDTO(ActivityTypeEntity activityTypeEntity) {
    return new ActivityTypeDTO(
      activityTypeEntity.getId(),
      activityTypeEntity.getActivityTypeName(),
      activityTypeEntity.getActivityTypeStatus()
    );
  }

  public static ActivityTypeEntity mapToEntity(ActivityTypeDTO activityTypeDTO) {
    return new ActivityTypeEntity(
      activityTypeDTO.getId(),
      activityTypeDTO.getActivityTypeName(),
      activityTypeDTO.getActivityTypeStatus()
    );
  }
}
