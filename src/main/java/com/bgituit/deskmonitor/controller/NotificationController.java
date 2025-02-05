package com.bgituit.deskmonitor.controller;

import com.bgituit.deskmonitor.domain.dto.CreateResponse;
import com.bgituit.deskmonitor.domain.dto.NotificationRequest;
import com.bgituit.deskmonitor.domain.dto.NotificationResponse;
import com.bgituit.deskmonitor.domain.dto.UpdateBooleanRequest;
import com.bgituit.deskmonitor.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
@Tag(name = "Уведомления")
public class NotificationController {
    private final NotificationService notificationService;

    @Operation(summary = "Информация о всех уведомлениях")
    @GetMapping("/get-all")
    public @ResponseBody NotificationResponse getAll() {
        return notificationService.getAll();
    }

    @Operation(summary = "Добавить уведомление")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse add(@RequestBody @Valid NotificationRequest request) {
        return notificationService.create(request);
    }

    @Operation(summary = "Удалить уведомление по ID (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        notificationService.deleteById(id);
    }

    @Operation(summary = "Изменить статус по ID (просмотрено ли)")
    @PutMapping("/update-status/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid UpdateBooleanRequest request) {
        notificationService.updateStatus(request);
    }
}
