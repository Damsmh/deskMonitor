package com.bgituit.deskmonitor.controller;

import com.bgituit.deskmonitor.domain.dto.*;
import com.bgituit.deskmonitor.service.AuthenticationService;
import com.bgituit.deskmonitor.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Operation(summary = "Регистрация пользователя (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/sign-up")
    public ProfileResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @Operation(summary = "Проверка валидности токена")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/valid")
    public @ResponseBody JwtValidResponse valid() {
        return jwtService.TokenValid();
    }
}
