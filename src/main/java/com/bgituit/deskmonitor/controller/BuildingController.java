package com.bgituit.deskmonitor.controller;

import com.bgituit.deskmonitor.domain.dto.BuildingRequest;
import com.bgituit.deskmonitor.domain.dto.BuildingResponse;
import com.bgituit.deskmonitor.domain.dto.CreateResponse;
import com.bgituit.deskmonitor.domain.model.Building;
import com.bgituit.deskmonitor.service.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/building")
@RequiredArgsConstructor
@Tag(name = "Корпусы")
public class BuildingController {
    private final BuildingService buildingService;

    @Operation(summary = "Информация обо всех корпусах")
    @GetMapping("/get-all")
    public @ResponseBody BuildingResponse getAll() {
        return buildingService.getAll();
    }

    @Operation(summary = "Добавить корпус (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")   
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse add(@RequestBody @Valid BuildingRequest request) {
        return buildingService.create(request);
    }

    @Operation(summary = "Удалить корпус по ID (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Integer id) {
        buildingService.deleteById(id);
    }

    @Operation(summary = "Изменить корпус (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid BuildingRequest request) {
        buildingService.update(request);
    }
}