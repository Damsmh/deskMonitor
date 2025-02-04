package com.bgituit.deskmonitor.service;

import com.bgituit.deskmonitor.domain.dto.*;
import com.bgituit.deskmonitor.domain.model.Breakdown;
import com.bgituit.deskmonitor.domain.model.Notification;
import com.bgituit.deskmonitor.domain.model.Response.BreakdownResponseModel;
import com.bgituit.deskmonitor.domain.model.Response.NotificationResponseModel;
import com.bgituit.deskmonitor.repository.BreakdownRepository;
import com.bgituit.deskmonitor.repository.ComputerRepository;
import com.bgituit.deskmonitor.repository.UserRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BreakdownService {
    private final BreakdownRepository repository;
    private final ComputerRepository computerRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher publisher;

    public Breakdown save(BreakdownRequest request) {
        var breakdown = Breakdown.builder()
                .description(request.getDescription())
                .date(request.getDate())
                .isSolved(request.getIsSolved())
                .computer(computerRepository.getReferenceById(request.getComputer()))
                .level(request.getLevel())
                .user(userRepository.getReferenceById(request.getUser()))
                .build();

        BreakdownResponseModel breakdownR = BreakdownResponseModel.builder()
                .id(breakdown.getId())
                .description(breakdown.getDescription())
                .date(breakdown.getDate())
                .isSolved(breakdown.getIsSolved())
                .computerId(breakdown.getComputer().getId())
                .userId(breakdown.getUser().getId())
                .level(breakdown.getLevel())
                .build();
        var createdBreakdown = repository.save(breakdown);
        publisher.publishEvent(breakdownR);
        return createdBreakdown;
    }

    public CreateResponse create(BreakdownRequest request) {
//        if (repository.existsByComputer(computerRepository.getReferenceById(request.getComputer()))
//                && repository.existsByLevel(request.getLevel())) {
//            throw new RuntimeException("Поломка с таким номером компьютера и уровнем уже существует");
//        }
        var breakdown = save(request);
        return new CreateResponse(breakdown.getId());
    }

    public void updateStatus(UpdateBooleanRequest request) {
        var breakdown = repository.getReferenceById(request.getId());
        breakdown.setIsSolved(request.getBoolValue());
        repository.save(breakdown);
    }

    public BreakdownResponse getAll() {
        List<Breakdown> breakdowns = repository.findAll();
        List<BreakdownResponseModel> result = new ArrayList<>();
        for (Breakdown breakdown : breakdowns) {
            BreakdownResponseModel breakdownR = BreakdownResponseModel.builder()
                    .id(breakdown.getId())
                    .computerId(breakdown.getComputer().getId())
                    .userId(breakdown.getUser().getId())
                    .level(breakdown.getLevel())
                    .isSolved(breakdown.getIsSolved())
                    .description(breakdown.getDescription())
                    .date(breakdown.getDate())
                    .build();
            result.add(breakdownR);
        }
        return new BreakdownResponse(result);
    }

    public void deleteById(Long id) { repository.deleteById(id); }
}
