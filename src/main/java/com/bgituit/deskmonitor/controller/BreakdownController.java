package com.bgituit.deskmonitor.controller;

import com.bgituit.deskmonitor.domain.dto.BreakdownRequest;
import com.bgituit.deskmonitor.domain.dto.BreakdownResponse;
import com.bgituit.deskmonitor.domain.dto.CreateResponse;
import com.bgituit.deskmonitor.domain.dto.UpdateBooleanRequest;
import com.bgituit.deskmonitor.domain.model.Breakdown;
import com.bgituit.deskmonitor.service.BreakdownService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@RestController
@RequestMapping("/breakdown")
@RequiredArgsConstructor
@Tag(name = "Поломки")
public class BreakdownController {
    private final BreakdownService breakdownService;

    private final Set<SseEmitter> clients = new CopyOnWriteArraySet<>();

    @GetMapping("/stream")
    public SseEmitter breakdownStream() {
        SseEmitter sseEmitter = new SseEmitter((long) (1000*60));
        clients.add(sseEmitter);

        sseEmitter.onTimeout(() -> clients.remove(sseEmitter));
        sseEmitter.onError(throwable -> clients.remove(sseEmitter));

        return sseEmitter;
    }

    @Async
    @EventListener
    public void breakdownMessageHandler(BreakdownResponse response) {
        List<SseEmitter> errorEmitters = new ArrayList<>();

        clients.forEach(emitter -> {
            try {
                emitter.send(response, MediaType.APPLICATION_JSON);
            } catch (Exception e) {
                errorEmitters.add(emitter);
            }
        });

        errorEmitters.forEach(clients::remove);
    }

    @Operation(summary = "Информация о всех поломках")
    @GetMapping("/get-all")
    public @ResponseBody BreakdownResponse getAll() {
        return breakdownService.getAll();
    }

    @Operation(summary = "Добавить поломку (Доступно только админам)")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse add(@RequestBody @Valid BreakdownRequest request) {
        return breakdownService.create(request);
    }

    @Operation(summary = "Удалить поломку по ID (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        breakdownService.deleteById(id);
    }

    @Operation(summary = "Изменить статус поломки по ID (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-status")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody UpdateBooleanRequest request) {
        breakdownService.updateStatus(request);
    }
}
