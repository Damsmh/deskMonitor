package com.bgituit.deskmonitor.service;

import com.bgituit.deskmonitor.domain.dto.ProfileRequest;
import com.bgituit.deskmonitor.domain.dto.ProfileResponse;
import com.bgituit.deskmonitor.domain.model.Role;
import com.bgituit.deskmonitor.domain.model.User;
import com.bgituit.deskmonitor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }

    public ProfileResponse setInfo(ProfileRequest request) {
        var user = getCurrentUser();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        save(user);
        return new ProfileResponse(user.getUsername(), user.getEmail());
    }

    public void setRole(Role role) {
        var user = getCurrentUser();
        user.setRole(role);
        save(user);
    }
}
