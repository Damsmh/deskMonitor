package com.bgituit.deskmonitor.service;

import com.bgituit.deskmonitor.domain.dto.AuditoriumRequest;
import com.bgituit.deskmonitor.domain.dto.BuildingRequest;
import com.bgituit.deskmonitor.domain.model.Auditorium;
import com.bgituit.deskmonitor.domain.model.Building;
import com.bgituit.deskmonitor.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingService {
    private final BuildingRepository repository;

    public Building save(BuildingRequest request) {
        var building = Building.builder()
                .number(request.getNumber())
                .floors(request.getFloors())
                .build();
        return repository.save(building);
    }

    public Building create(BuildingRequest request) {
        if (repository.existsByNumber(request.getNumber())) {
            throw new RuntimeException("Корпус с таким номером уже существует");
        }
        return save(request);
    }

    public Building getByNumber(Integer number) {
        return repository.findByNumber(number)
                .orElseThrow(() -> new RuntimeException("Корпус не найден"));

    }

    public void update(BuildingRequest request) {
        var building = this.getByNumber(request.getNumber());
        building.setFloors(request.getFloors());
        repository.save(building);
    }

    public List<Building> getAll() { return repository.findAll(); }

    public void deleteById(Integer id) { repository.deleteById(id); }

}
