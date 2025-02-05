package com.bgituit.deskmonitor.service;

import com.bgituit.deskmonitor.domain.dto.AuditoriumRequest;
import com.bgituit.deskmonitor.domain.dto.AuditoriumResponse;
import com.bgituit.deskmonitor.domain.dto.AuditoriumUpdateRequest;
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
                .name(request.getName())
                .isComputer(request.getIsComputer())
                .floor(request.getFloor())
                .size(request.getSize())
                .position(request.getPosition())
                .building(BuildingRepository.getReferenceById(request.getBuildingId()))
                .build();
        return repository.save(auditorium);
    }

    public CreateResponse create(AuditoriumRequest request) {
        var auditorium = save(request);
        return new CreateResponse(auditorium.getId());
    }

    public void update(AuditoriumUpdateRequest request) {
        var auditorium = repository.getReferenceById(request.getId());
        auditorium.setIsComputer(request.getIsComputer());
        auditorium.setName(request.getName());
        auditorium.setBuilding(BuildingRepository.getReferenceById(request.getBuildingId()));
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
                    .name(auditorium.getName())
                    .isComputer(auditorium.getIsComputer())
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
