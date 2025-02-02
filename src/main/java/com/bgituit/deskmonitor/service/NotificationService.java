package com.bgituit.deskmonitor.service;

import com.bgituit.deskmonitor.domain.dto.NotificationRequest;
import com.bgituit.deskmonitor.domain.model.Notification;
import com.bgituit.deskmonitor.repository.BreakdownRepository;
import com.bgituit.deskmonitor.repository.NotificationRepository;
import com.bgituit.deskmonitor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;
    private final BreakdownRepository breakdownRepository;
    private final UserRepository userRepository;

    public Notification save(NotificationRequest request) {
        var notification = Notification.builder()
                .breakdown(breakdownRepository.getReferenceById(request.getBreakdown()))
                .user(userRepository.getReferenceById(request.getUser()))
                .isViewed(request.getIsViewed())
                .build();
        return repository.save(notification);
    }

    public Notification create(NotificationRequest request) {
        if (repository.existsByBreakdown(breakdownRepository.getReferenceById(request.getBreakdown()))
                && repository.existsByUser(userRepository.getReferenceById(request.getUser()))) {
            throw new RuntimeException("Уведомление с таким номером поломки и пользователем уже существует");
        }
        return save(request);
    }

    public void updateStatus(Long id) {
        var notification = repository.getReferenceById(id);
        notification.setIsViewed(true);
        repository.save(notification);
    }

    public List<Notification> getAll() { return repository.findAll(); }

    public void deleteById(Long id) { repository.deleteById(id); }
}
