package com.bgituit.deskmonitor.service;

import com.bgituit.deskmonitor.domain.dto.BreakdownRequest;
import com.bgituit.deskmonitor.domain.model.Breakdown;
import com.bgituit.deskmonitor.repository.BreakdownRepository;
import com.bgituit.deskmonitor.repository.ComputerRepository;
import com.bgituit.deskmonitor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreakdownService {
    private final BreakdownRepository repository;
    private final ComputerRepository computerRepository;
    private final UserRepository userRepository;

    public Breakdown save(BreakdownRequest request) {
        var breakdown = Breakdown.builder()
                .computer(computerRepository.getReferenceById(request.getComputer()))
                .level(request.getLevel())
                .user(userRepository.getReferenceById(request.getUser()))
                .build();
        return repository.save(breakdown);
    }

    public Breakdown create(BreakdownRequest request) {
        if (repository.existsByComputer(computerRepository.getReferenceById(request.getComputer()))
                && repository.existsByLevel(request.getLevel())) {
            throw new RuntimeException("Поломка с таким номером компьютера и уровнем уже существует");
        }
        return save(request);
    }

    public void updateStatus(Long id) {
        var breakdown = repository.getReferenceById(id);
        breakdown.setIsSolved(true);
        repository.save(breakdown);
    }

    public List<Breakdown> getAll() { return repository.findAll(); }

    public void deleteById(Long id) { repository.deleteById(id); }
}
