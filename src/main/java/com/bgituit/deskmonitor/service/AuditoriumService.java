package com.bgituit.deskmonitor.service;


import com.bgituit.deskmonitor.domain.dto.AuditoriumRequest;
import com.bgituit.deskmonitor.domain.dto.AuditoriumResponse;
import com.bgituit.deskmonitor.domain.dto.CreateResponse;
import com.bgituit.deskmonitor.domain.model.Auditorium;
import com.bgituit.deskmonitor.domain.model.Response.AuditoriumResponseModel;
import com.bgituit.deskmonitor.repository.AuditoriumRepository;
import com.bgituit.deskmonitor.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                .position(request.getPosition())
                .building(BuildingRepository.getReferenceById(request.getBuilding()))
                .build();
        return repository.save(auditorium);
    }

    public CreateResponse create(AuditoriumRequest request) {
        if (repository.existsByNumber(request.getNumber())) {
            throw new RuntimeException("Аудитория с таким номером уже существует");
        }
        var auditorium = save(request);
        return new CreateResponse(auditorium.getId());
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

    public AuditoriumResponse getAll() {
        List<Auditorium> auditoriums = repository.findAll();
        List<AuditoriumResponseModel> result = new ArrayList<>();
        for (Auditorium auditorium : auditoriums) {
            AuditoriumResponseModel auditoriumR = AuditoriumResponseModel.builder()
                    .id(auditorium.getId())
                    .number(auditorium.getNumber())
                    .floor(auditorium.getFloor())
                    .size(auditorium.getSize())
                    .position(auditorium.getPosition())
                    .buildingId(auditorium.getBuilding().getId())
                    .build();
            result.add(auditoriumR);
        }
        return new AuditoriumResponse(result);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
