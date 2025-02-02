package com.bgituit.deskmonitor.repository;

import com.bgituit.deskmonitor.domain.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
    Optional<Building> findByNumber(Integer number);
    boolean existsByNumber(Integer number);
}
