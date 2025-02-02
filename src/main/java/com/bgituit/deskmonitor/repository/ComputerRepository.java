package com.bgituit.deskmonitor.repository;

import com.bgituit.deskmonitor.domain.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
    Optional<Computer> findBySerialNumber(String serialNumber);
    boolean existsBySerialNumber(String serialNumber);
}
