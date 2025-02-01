package com.bgituit.deskmonitor.controller;


import com.bgituit.deskmonitor.domain.dto.AuditoriumRequest;
import com.bgituit.deskmonitor.domain.dto.AuditoriumResponse;
import com.bgituit.deskmonitor.domain.dto.ProfileRequest;
import com.bgituit.deskmonitor.domain.dto.ProfileResponse;
import com.bgituit.deskmonitor.domain.model.Auditorium;
import com.bgituit.deskmonitor.service.AuditoriumService;
import com.bgituit.deskmonitor.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@RestController
@RequestMapping("/aud")
@RequiredArgsConstructor
@Tag(name = "Аудитории")
public class AuditoriumController {
    private final AuditoriumService service;

    @Operation(summary = "Информация обо всех аудиториях")
    @GetMapping("/get-all")
    public @ResponseBody List<Auditorium> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Добавить аудиторию (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid AuditoriumRequest request) {
        service.create(request);
    }

}
