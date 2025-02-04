package com.bgituit.deskmonitor.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на изменение информации о компьютере")
@AllArgsConstructor
@NoArgsConstructor
public class ComputerRequest {
    @Schema(description = "Серийный номер", example = "3KJ5B6UJHV3V54KJ4BH34VI")
    private String serialNumber;

    @Schema(description = "ID аудитории" , example = "35")
    private Long auditoriumId;

    @Schema(description = "Позиция", example = "125;30")
    private String position;

    @Schema(description = "Размер", example = "125*30")
    private String size;
}
