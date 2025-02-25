package com.bgituit.deskmonitor.controller;


import com.bgituit.deskmonitor.domain.dto.AuditoriumRequest;
import com.bgituit.deskmonitor.domain.dto.AuditoriumResponse;
import com.bgituit.deskmonitor.domain.dto.AuditoriumUpdateRequest;
import com.bgituit.deskmonitor.domain.dto.CreateResponse;
import com.bgituit.deskmonitor.service.AuditoriumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aud")
@RequiredArgsConstructor
@Tag(name = "Аудитории")
public class AuditoriumController {
    private final AuditoriumService Auditoriumservice;

    @Operation(summary = "Информация обо всех аудиториях")
    @GetMapping("/get-all")
    public @ResponseBody AuditoriumResponse getAll() {
        return Auditoriumservice.getAll();
    }

    @Operation(summary = "Добавить аудиторию (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse add(@RequestBody @Valid AuditoriumRequest request) {
        return Auditoriumservice.create(request);
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
    public void update(@RequestBody @Valid AuditoriumUpdateRequest request) {
        Auditoriumservice.update(request);
    }

}
