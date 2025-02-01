package com.bgituit.deskmonitor.repository;


import com.bgituit.deskmonitor.domain.model.Auditorium;
import com.bgituit.deskmonitor.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
    Optional<Auditorium> findByNumber(Integer number);
    boolean existsByNumber(Integer number);

}
