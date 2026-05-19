package com.MosIC.MosIC_Office.employee_position.repository;

import com.MosIC.MosIC_Office.employee_position.entity.EmpPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpPositionRepository extends JpaRepository<EmpPositionEntity, Long> {

    List<EmpPositionEntity> findByEmpId(Long empId);

    List<EmpPositionEntity> findByActiveStatus(Integer activeStatus);

    List<EmpPositionEntity> findByEmpIdAndActiveStatus(Long empId, Integer activeStatus);
}