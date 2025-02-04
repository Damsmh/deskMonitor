package com.bgituit.deskmonitor.controller;

import com.bgituit.deskmonitor.domain.dto.ComputerRequest;
import com.bgituit.deskmonitor.domain.dto.ComputerResponse;
import com.bgituit.deskmonitor.domain.dto.CreateResponse;
import com.bgituit.deskmonitor.domain.model.Computer;
import com.bgituit.deskmonitor.service.ComputerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/computer")
@RequiredArgsConstructor
@Tag(name = "Компьютеры")
public class ComputerController {
    private final ComputerService computerService;

    @Operation(summary = "Информация о всех компьютерах")
    @GetMapping("/get-all")
    public @ResponseBody ComputerResponse getAll() {
        return computerService.getAll();
    }

    @Operation(summary = "Добавить компьютер (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse add(@RequestBody @Valid ComputerRequest request) {
        return computerService.create(request);
    }

    @Operation(summary = "Удалить компьютер по ID (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        computerService.deleteById(id);
    }

    @Operation(summary = "Изменить информацию о компьютере (Доступно только админам)")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid  ComputerRequest request) {
        computerService.update(request);
    }
}
