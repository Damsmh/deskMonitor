package com.bgituit.deskmonitor.service;

import com.bgituit.deskmonitor.domain.dto.*;
import com.bgituit.deskmonitor.domain.model.Breakdown;
import com.bgituit.deskmonitor.domain.model.Response.BreakdownResponseModel;
import com.bgituit.deskmonitor.repository.BreakdownRepository;
import com.bgituit.deskmonitor.repository.ComputerRepository;
import com.bgituit.deskmonitor.repository.UserRepository;
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
                .date(new Date(System.currentTimeMillis()))
                .isSolved(request.getIsSolved())
                .computer(computerRepository.getReferenceById(request.getComputerId()))
                .level(request.getLevel())
                .user(userRepository.getReferenceById(request.getUserId()))
                .build();
        var createdBreakdown = repository.save(breakdown);
        publisher.publishEvent(getAll());
        return createdBreakdown;
    }

    public CreateResponse create(BreakdownRequest request) {
        var breakdown = save(request);
        return new CreateResponse(breakdown.getId());
    }

    public void updateStatus(UpdateBooleanRequest request) {
        var breakdown = repository.getReferenceById(request.getId());
        breakdown.setIsSolved(request.getBoolValue());
        repository.save(breakdown);
        publisher.publishEvent(getAll());
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

    public void deleteById(Long id) {
        repository.deleteById(id);
        publisher.publishEvent(getAll());
    }
}
