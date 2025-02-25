package com.bgituit.deskmonitor.service;

import com.bgituit.deskmonitor.domain.dto.JwtAuthenticationResponse;
import com.bgituit.deskmonitor.domain.dto.ProfileResponse;
import com.bgituit.deskmonitor.domain.dto.SignInRequest;
import com.bgituit.deskmonitor.domain.dto.SignUpRequest;
import com.bgituit.deskmonitor.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ProfileResponse signUp(SignUpRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userService.create(user);
        return new ProfileResponse(user.getUsername(), user.getEmail(), user.getRole());
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
