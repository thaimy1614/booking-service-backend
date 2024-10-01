package com.s_service.s_service.repository;

import com.s_service.s_service.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository<Stage, Integer> {
}
