package com.bgituit.deskmonitor.service;

import com.bgituit.deskmonitor.domain.dto.*;
import com.bgituit.deskmonitor.domain.model.Notification;
import com.bgituit.deskmonitor.domain.model.Response.NotificationResponseModel;
import com.bgituit.deskmonitor.repository.BreakdownRepository;
import com.bgituit.deskmonitor.repository.NotificationRepository;
import com.bgituit.deskmonitor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public CreateResponse create(NotificationRequest request) {
        if (repository.existsByBreakdown(breakdownRepository.getReferenceById(request.getBreakdown()))
                && repository.existsByUser(userRepository.getReferenceById(request.getUser()))) {
            throw new RuntimeException("Уведомление с таким номером поломки и пользователем уже существует");
        }
        var notification = save(request);
        return new CreateResponse(notification.getId());
    }

    public void updateStatus(UpdateBooleanRequest request) {
        var notification = repository.getReferenceById(request.getId());
        notification.setIsViewed(request.getBoolValue());
        repository.save(notification);
    }


    public NotificationResponse getAll() {
        List<Notification> notifications = repository.findAll();
        List<NotificationResponseModel> result = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationResponseModel notify = NotificationResponseModel.builder()
                    .id(notification.getId())
                    .breakdownId(notification.getBreakdown().getId())
                    .userId(notification.getUser().getId())
                    .isViewed(notification.getIsViewed())
                    .build();
            result.add(notify);
        }
        return new NotificationResponse(result);
    }

    public void deleteById(Long id) { repository.deleteById(id); }
}
