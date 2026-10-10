package com.smartplacement.repository;
import com.smartplacement.entity.PlacementResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface PlacementResultRepository extends JpaRepository<PlacementResult,Long>{ Optional<PlacementResult> findByApplicationId(Long applicationId); }
