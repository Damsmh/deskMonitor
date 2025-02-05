package com.bgituit.deskmonitor.service;

import com.bgituit.deskmonitor.domain.dto.*;
import com.bgituit.deskmonitor.domain.model.Computer;
import com.bgituit.deskmonitor.domain.model.Response.ComputerResponseModel;
import com.bgituit.deskmonitor.repository.AuditoriumRepository;
import com.bgituit.deskmonitor.repository.ComputerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComputerService {
    private final ComputerRepository repository;
    private final AuditoriumRepository auditoriumRepository;

    public Computer save(ComputerRequest request) {
        var computer = Computer.builder()
                .position(request.getPosition())
                .size(request.getSize())
                .serialNumber(request.getSerialNumber())
                .auditorium(auditoriumRepository.getReferenceById(request.getAuditoriumId()))
                .build();
        return repository.save(computer);
    }

    public CreateResponse create(ComputerRequest request) {
        if (repository.existsBySerialNumber(request.getSerialNumber())) {
            throw new RuntimeException("Компьютер с таким номером уже существует");
        }
        var computer = save(request);
        return new CreateResponse(computer.getId());
    }

    @Deprecated
    public Computer getBySerialNumber(String number) {
        return repository.findBySerialNumber(number)
                .orElseThrow(() -> new RuntimeException("Компьютер не найден"));

    }

    public void update(ComputerUpdateRequest request) {
        var computer = repository.getReferenceById(request.getId());
        computer.setSerialNumber(request.getSerialNumber());
        computer.setAuditorium(auditoriumRepository.getReferenceById(request.getAuditoriumId()));
        computer.setPosition(request.getPosition());
        computer.setSize(request.getSize());
        repository.save(computer);
    }

    public ComputerResponse getAll() {
        List<Computer> computers = repository.findAll();
        List<ComputerResponseModel> result = new ArrayList<>();
        for (Computer computer : computers) {
            ComputerResponseModel computerR = ComputerResponseModel.builder()
                    .id(computer.getId())
                    .serialNumber(computer.getSerialNumber())
                    .auditoriumId(computer.getAuditorium().getId())
                    .position(computer.getPosition())
                    .size(computer.getSize())
                    .build();
            result.add(computerR);
        }
        return new ComputerResponse(result);
    }

    public void deleteById(Long id) { repository.deleteById(id); }
}
