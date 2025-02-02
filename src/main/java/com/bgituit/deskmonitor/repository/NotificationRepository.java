package com.bgituit.deskmonitor.repository;

import com.bgituit.deskmonitor.domain.model.Breakdown;
import com.bgituit.deskmonitor.domain.model.Notification;
import com.bgituit.deskmonitor.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    boolean existsByBreakdown(Breakdown breakdown);
    boolean existsByUser(User user);
}
