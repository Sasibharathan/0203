package com.MosIC.MosIC_Office.activity_type.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.MosIC.MosIC_Office.activity_type.dto.ActivityTypeDTO;

@Service
public interface ActivityTypeServices {
  ActivityTypeDTO createActivityType(ActivityTypeDTO activityTypeDTO);

  ActivityTypeDTO getActivityTypeById(Long id);

  List<ActivityTypeDTO> getAllActivityTypes();

  ActivityTypeDTO updateActivityType(Long id, ActivityTypeDTO activityTypeDTO);

  void deleteActivityType(Long id);

}
