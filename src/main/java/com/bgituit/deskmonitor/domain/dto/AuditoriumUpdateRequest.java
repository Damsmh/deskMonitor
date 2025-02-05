package com.bgituit.deskmonitor.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на изменение информации об аудитории")
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriumUpdateRequest {
    @Schema(description = "ID аудитории", example = "32")
    private Long id;

    @Schema(description = "Номер аудитории", example = "314")
    private Integer number;

    @Schema(description = "Этаж", example = "3")
    private Integer floor;

    @Schema(description = "Размер" , example = "40*50")
    private String size;

    @Schema(description = "Позиция" , example = "100;50")
    private String position;

    @Schema(description = "Корпус", example = "1")
    private Integer building;
}
