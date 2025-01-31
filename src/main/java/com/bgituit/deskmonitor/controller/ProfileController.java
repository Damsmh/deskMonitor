package com.bgituit.deskmonitor.controller;


import com.bgituit.deskmonitor.domain.dto.ProfileRequest;
import com.bgituit.deskmonitor.domain.dto.ProfileResponse;
import com.bgituit.deskmonitor.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@Tag(name = "Профиль")
public class ProfileController {
    private final UserService service;

    @Operation(summary = "Информация о пользователе")
    @GetMapping
    public ProfileResponse getUserInfo() {
        var user = service.getCurrentUser();
        return new ProfileResponse(user.getUsername(), user.getEmail());
    }

    @Operation(summary = "Изменение информации о пользователе")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ProfileResponse setInfo(@RequestBody @Valid ProfileRequest request) {
        return service.setInfo(request);
    }

}
