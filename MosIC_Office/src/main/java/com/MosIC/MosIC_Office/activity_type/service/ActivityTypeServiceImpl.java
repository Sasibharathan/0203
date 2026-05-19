package com.MosIC.MosIC_Office.activity_type.service;

import com.MosIC.MosIC_Office.activity_type.dto.ActivityTypeDTO;
import com.MosIC.MosIC_Office.activity_type.entity.ActivityTypeEntity;
import com.MosIC.MosIC_Office.activity_type.mapper.ActivityTypeMapper;
import com.MosIC.MosIC_Office.activity_type.repository.ActivityTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityTypeServiceImpl implements ActivityTypeServices {

    private final ActivityTypeRepository activityTypeRepository;

    public ActivityTypeServiceImpl(ActivityTypeRepository activityTypeRepository) {
        this.activityTypeRepository = activityTypeRepository;
    }

    // ── CREATE ────────────────────────────────────────────────────────────────
    @Override
    public ActivityTypeDTO createActivityType(ActivityTypeDTO activityTypeDTO) {
        if (activityTypeRepository.existsByActivityTypeName(activityTypeDTO.getActivityTypeName())) {
            throw new IllegalArgumentException(
                "Activity type already exists: " + activityTypeDTO.getActivityTypeName()
            );
        }
        ActivityTypeEntity entity = ActivityTypeMapper.mapToEntity(activityTypeDTO);
        ActivityTypeEntity saved  = activityTypeRepository.save(entity);
        return ActivityTypeMapper.mapToDTO(saved);
    }

    // ── GET BY ID ─────────────────────────────────────────────────────────────
    @Override
    public ActivityTypeDTO getActivityTypeById(Long id) {
        ActivityTypeEntity entity = activityTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity type not found with id: " + id));
        return ActivityTypeMapper.mapToDTO(entity);
    }

    // ── GET ALL ───────────────────────────────────────────────────────────────
    @Override
    public List<ActivityTypeDTO> getAllActivityTypes() {
        return activityTypeRepository.findAll()
                .stream()
                .map(ActivityTypeMapper::mapToDTO)
                .toList();
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    @Override
    public ActivityTypeDTO updateActivityType(Long id, ActivityTypeDTO activityTypeDTO) {
        ActivityTypeEntity entity = activityTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity type not found with id: " + id));

        entity.setActivityTypeName(activityTypeDTO.getActivityTypeName());
        entity.setActivityTypeStatus(activityTypeDTO.getActivityTypeStatus());

        ActivityTypeEntity updated = activityTypeRepository.save(entity);
        return ActivityTypeMapper.mapToDTO(updated);
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    @Override
    public void deleteActivityType(Long id) {
        ActivityTypeEntity entity = activityTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity type not found with id: " + id));
        activityTypeRepository.delete(entity);
    }
}