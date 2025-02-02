package com.bgituit.deskmonitor.repository;

import com.bgituit.deskmonitor.domain.model.Breakdown;
import com.bgituit.deskmonitor.domain.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreakdownRepository extends JpaRepository<Breakdown, Long> {
    boolean existsByComputer(Computer computer);
    boolean existsByLevel(Integer level);
}
