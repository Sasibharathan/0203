package com.MosIC.MosIC_Office.activity_package.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MosIC.MosIC_Office.activity_package.dto.ActivityDTO;
import com.MosIC.MosIC_Office.activity_package.entity.ActivityEntity;
import com.MosIC.MosIC_Office.activity_package.mapper.ActivityMapper;
import com.MosIC.MosIC_Office.activity_package.repository.ActivityRepository;


@Service
public class ActivityServiceImp implements ActivityServices {
    private final ActivityRepository activityRepository;

    // ✅ CONSTRUCTOR INJECTION (IMPORTANT)
    public ActivityServiceImp(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        ActivityEntity entity = new ActivityEntity();
        entity.setActivityDate(activityDTO.getActivityDate());
        entity.setActivityDocId(activityDTO.getActivityDocId());
        entity.setActivityDocTable(activityDTO.getActivityDocTable());
        entity.setActivityReferenceNo(activityDTO.getActivityReferenceNo());
        entity.setActivityRemarks(activityDTO.getActivityRemarks());
        entity.setActivityStatus(activityDTO.getActivityStatus());

        ActivityEntity saved = activityRepository.save(entity);
        activityDTO.setId(saved.getId());
        return activityDTO;

    }

    @Override
    public ActivityDTO getActivityById(Long id) {
        ActivityEntity activityEntity = activityRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
        System.out.println("ActivityEntity found: " + activityEntity);
        return ActivityMapper.mapToDTO(activityEntity);
    }

    public List<ActivityDTO> getAllActivities() {
        List<ActivityEntity> activityEntities = activityRepository.findAll();
        return activityEntities.stream()
                .map(ActivityMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ActivityDTO updateActivity(Long id, ActivityDTO activityDTO) {
        ActivityEntity activityEntity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));

        activityEntity.setActivityDate(activityDTO.getActivityDate());
        activityEntity.setActivityDocId(activityDTO.getActivityDocId());
        activityEntity.setActivityDocTable(activityDTO.getActivityDocTable());
        activityEntity.setActivityReferenceNo(activityDTO.getActivityReferenceNo());
        activityEntity.setActivityRemarks(activityDTO.getActivityRemarks());
        activityEntity.setActivityStatus(activityDTO.getActivityStatus());

        ActivityEntity updated = activityRepository.save(activityEntity);
        return ActivityMapper.mapToDTO(updated);
    }
    @Override
    public void deleteActivity(Long id) {
        ActivityEntity activityEntity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
        activityRepository.delete(activityEntity);
    }

    @Override
    public List<ActivityDTO> getActivitiesByRefNo(String refFileNo) {
        List<ActivityEntity> activityEntities = activityRepository.findByActivityReferenceNo(refFileNo);
        return activityEntities.stream()
                .map(ActivityMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ @Transactional is REQUIRED for delete by non-ID field
    @Override
    @Transactional
    public void deleteActivitiesByRefNo(String refFileNo) {
        activityRepository.deleteByActivityReferenceNo(refFileNo);
    }


}
