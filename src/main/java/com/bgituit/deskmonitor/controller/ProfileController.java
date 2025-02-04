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

    @Operation(summary = "Информация о пользователе")
    @GetMapping
    public ProfileResponse getUserInfo() {
        var user = userService.getCurrentUser();
        return new ProfileResponse(user.getUsername(), user.getEmail());
    }

    @Operation(summary = "Изменение информации о пользователе")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void setInfo(@RequestBody @Valid ProfileRequest request) {
        userService.setInfo(request);
    }

}
