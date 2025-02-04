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
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breakdown")
@RequiredArgsConstructor
@Tag(name = "Поломки")
public class BreakdownController {
    private final BreakdownService breakdownService;

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
