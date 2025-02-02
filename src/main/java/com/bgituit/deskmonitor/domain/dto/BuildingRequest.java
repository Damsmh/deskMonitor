package com.bgituit.deskmonitor.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на изменение информации о корпусе")
@AllArgsConstructor
@NoArgsConstructor
public class BuildingRequest {
    @Schema(description = "Номер корпуса", example = "1")
    private Integer number;

    @Schema(description = "Кол-во этажей", example = "4")
    private Integer floors;

}
