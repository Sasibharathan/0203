package com.MosIC.MosIC_Office.activity_package.service;

import java.util.List;

import com.MosIC.MosIC_Office.activity_package.dto.ActivityDTO;

public interface ActivityServices {

  ActivityDTO createActivity(ActivityDTO activityDTO);

  ActivityDTO getActivityById(Long id);

  // Get all activities that share the same Ref_file_no
  List<ActivityDTO> getActivitiesByRefNo(String refFileNo);

  //List<ActivityDTO> getAllActivities();

  ActivityDTO updateActivity(Long id, ActivityDTO activityDTO);

  void deleteActivity(Long id);

  // Delete ALL activities that belong to a ref file number
  void deleteActivitiesByRefNo(String refFileNo);
}
