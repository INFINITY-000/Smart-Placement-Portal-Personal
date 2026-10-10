package com.smartplacement.repository;
import com.smartplacement.entity.PlacementOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface PlacementOfficerRepository extends JpaRepository<PlacementOfficer,Long>{ Optional<PlacementOfficer> findByEmail(String email); }
