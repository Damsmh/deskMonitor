package com.bgituit.deskmonitor.controller;


import com.bgituit.deskmonitor.domain.dto.AuditoriumRequest;
import com.bgituit.deskmonitor.domain.model.Auditorium;
import com.bgituit.deskmonitor.service.AuditoriumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aud")
@RequiredArgsConstructor
@Tag(name = "Аудитории")
public class AuditoriumController {
    private final AuditoriumService Auditoriumservice;

    @Operation(summary = "Информация обо всех аудиториях")
    @GetMapping("/get-all")
    public @ResponseBody List<Auditorium> getAll() {
        return Auditoriumservice.getAll();
    }

    @Operation(summary = "Добавить аудиторию (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid AuditoriumRequest request) {
        Auditoriumservice.create(request);
    }

    @Operation(summary = "Удалить аудиторию по id (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        Auditoriumservice.deleteById(id);
    }

    @Operation(summary = "Изменить аудиторию (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid AuditoriumRequest request) {
        Auditoriumservice.update(request);
    }

}
