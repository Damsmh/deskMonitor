package com.bgituit.deskmonitor.service;


import com.bgituit.deskmonitor.domain.dto.AuditoriumRequest;
import com.bgituit.deskmonitor.domain.model.Auditorium;
import com.bgituit.deskmonitor.repository.AuditoriumRepository;
import com.bgituit.deskmonitor.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriumService {
    private final AuditoriumRepository repository;
    private final BuildingRepository BuildingRepository;

    public Auditorium save(AuditoriumRequest request) {
        var auditorium = Auditorium.builder()
                .number(request.getNumber())
                .floor(request.getFloor())
                .size(request.getSize())
                .building(BuildingRepository.getReferenceById(request.getBuilding()))
                .build();
        return repository.save(auditorium);
    }

    public Auditorium create(AuditoriumRequest request) {
        if (repository.existsByNumber(request.getNumber())) {
            throw new RuntimeException("Аудитория с таким номером уже существует");
        }
        return save(request);
    }

    public Auditorium getByNumber(Integer number) {
        return repository.findByNumber(number)
                .orElseThrow(() -> new RuntimeException("Аудитория не найдена"));
    }

    public void update(AuditoriumRequest request) {
        var auditorium = this.getByNumber(request.getNumber());
        auditorium.setBuilding(BuildingRepository.getReferenceById(request.getBuilding()));
        auditorium.setSize(request.getSize());
        auditorium.setFloor(request.getFloor());
        repository.save(auditorium);
    }

    public List<Auditorium> getAll() { return repository.findAll(); }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
