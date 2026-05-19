package com.MosIC.MosIC_Office.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MosIC.MosIC_Office.auth.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByGmail(String gmail);

    boolean existsByGmail(String gmail);

    boolean existsByUsername(String username);
}
