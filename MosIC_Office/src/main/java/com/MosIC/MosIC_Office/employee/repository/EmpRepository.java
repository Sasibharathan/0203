package com.MosIC.MosIC_Office.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.MosIC.MosIC_Office.employee.entity.EmpEntity;

public interface EmpRepository extends JpaRepository<EmpEntity, Long> {

}
