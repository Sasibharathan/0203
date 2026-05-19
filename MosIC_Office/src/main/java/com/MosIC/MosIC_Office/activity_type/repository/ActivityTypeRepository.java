package com.MosIC.MosIC_Office.activity_type.repository;

import com.MosIC.MosIC_Office.activity_type.entity.ActivityTypeEntity;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityTypeRepository extends JpaRepository<ActivityTypeEntity, Long> {

    boolean existsByActivityTypeName(String activityTypeName);

    Optional<ActivityTypeEntity> findByActivityTypeName(String activityTypeName);
}