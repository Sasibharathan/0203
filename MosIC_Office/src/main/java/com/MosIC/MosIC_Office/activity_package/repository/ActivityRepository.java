package com.MosIC.MosIC_Office.activity_package.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MosIC.MosIC_Office.activity_package.entity.ActivityEntity;

import jakarta.transaction.Transactional;


public interface ActivityRepository  extends JpaRepository<ActivityEntity, Long>{
    List<ActivityEntity> findByActivityReferenceNo(String activityReferenceNo);

    // Delete ALL activities for a file ref (used when a whole file is removed)
    @Transactional
    void deleteByActivityReferenceNo(String activityReferenceNo);

    // Delete only the activity rows that belong to one specific document
    // e.g. doc_id = "42" AND doc_table = "sales_register"
    @Transactional
    void deleteByActivityDocIdAndActivityDocTable(String activityDocId, String activityDocTable);

}
