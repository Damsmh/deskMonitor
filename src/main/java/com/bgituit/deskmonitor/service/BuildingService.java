package com.bgituit.deskmonitor.service;

import com.bgituit.deskmonitor.domain.dto.*;
import com.bgituit.deskmonitor.domain.model.Building;
import com.bgituit.deskmonitor.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public CreateResponse create(BuildingRequest request) {
        if (repository.existsByNumber(request.getNumber())) {
            throw new RuntimeException("Корпус с таким номером уже существует");
        }
        var building = save(request);
        return new CreateResponse(building.getId());
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

    public BuildingResponse getAll() { return new BuildingResponse(repository.findAll()); }

    public void deleteById(Integer id) { repository.deleteById(id); }

}
