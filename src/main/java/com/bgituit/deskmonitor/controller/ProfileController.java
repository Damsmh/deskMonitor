package com.bgituit.deskmonitor.controller;

import com.bgituit.deskmonitor.domain.dto.ProfileAllResponse;
import com.bgituit.deskmonitor.domain.dto.ProfileRequest;
import com.bgituit.deskmonitor.domain.dto.ProfileResponse;
import com.bgituit.deskmonitor.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@Tag(name = "Профиль")
public class ProfileController {
    private final UserService userService;

    @Operation(summary = "Список пользователей")
    @GetMapping("/get-all")
    public ProfileAllResponse getAllProfile() {
        return new ProfileAllResponse(userService.getAllProfiles());
    }

    @Operation(summary = "Удаление пользователя по ID")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @Operation(summary = "Информация о пользователе")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ProfileResponse getUserInfo() {
        var user = userService.getCurrentUser();
        return new ProfileResponse(user.getUsername(), user.getEmail(), user.getRole());
    }

    @Operation(summary = "Изменение информации о пользователе")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void setInfo(@RequestBody @Valid ProfileRequest request) {
        userService.setInfo(request);
    }

    @Operation(summary = "Изменение информации о пользователе по ID (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void setInfoById(@PathVariable Long id, @RequestBody @Valid ProfileRequest request) {
        userService.setInfoById(id, request);
    }
}
