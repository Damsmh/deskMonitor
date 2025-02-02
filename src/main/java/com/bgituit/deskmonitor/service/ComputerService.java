package com.bgituit.deskmonitor.service;

import com.bgituit.deskmonitor.domain.dto.ComputerRequest;
import com.bgituit.deskmonitor.domain.model.Computer;
import com.bgituit.deskmonitor.repository.AuditoriumRepository;
import com.bgituit.deskmonitor.repository.ComputerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComputerService {
    private final ComputerRepository repository;
    private final AuditoriumRepository auditoriumRepository;

    public Computer save(ComputerRequest request) {
        var computer = Computer.builder()
                .serialNumber(request.getSerialNumber())
                .auditorium(auditoriumRepository.getReferenceById(request.getAuditorium()))
                .build();
        return repository.save(computer);
    }

    public Computer create(ComputerRequest request) {
        if (repository.existsBySerialNumber(request.getSerialNumber())) {
            throw new RuntimeException("Компьютер с таким номером уже существует");
        }
        return save(request);
    }

    public Computer getBySerialNumber(String number) {
        return repository.findBySerialNumber(number)
                .orElseThrow(() -> new RuntimeException("Компьютер не найден"));

    }

    public void update(ComputerRequest request) {
        var computer = this.getBySerialNumber(request.getSerialNumber());
        computer.setAuditorium(auditoriumRepository.getReferenceById(request.getAuditorium()));
        computer.setPosition(request.getPosition());
        repository.save(computer);
    }

    public List<Computer> getAll() { return repository.findAll(); }

    public void deleteById(Long id) { repository.deleteById(id); }
}
