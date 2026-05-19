package com.MosIC.MosIC_Office.matpass.repository;

import com.MosIC.MosIC_Office.matpass.entity.MatpassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatpassRepository extends JpaRepository<MatpassEntity, Long> {

    List<MatpassEntity> findByStatus(Integer status);

    // ✅ @Query bypasses Spring's method name parser entirely
    @Query("SELECT m FROM MatpassEntity m WHERE m.inOrOut = :inOrOut")
    List<MatpassEntity> findByInOrOut(@Param("inOrOut") String inOrOut);

    List<MatpassEntity> findByPartyContainingIgnoreCase(String party);
}