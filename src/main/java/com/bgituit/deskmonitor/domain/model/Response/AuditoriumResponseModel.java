package com.bgituit.deskmonitor.domain.model.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuditoriumResponseModel {
    @Schema(description = "ID аудитории", example = "12")
    private Long id;

    @Schema(description = "Название аудитории", example = "Аудитория 228")
    private String name;

    @Schema(description = "Тип аудитории", example = "true")
    private Boolean isComputer;

    @Schema(description = "Этаж", example = "2")
    private Integer floor;

    @Schema(description = "Размер", example = "300*400")
    private String size;

    @Schema(description = "Позиция", example = "300;400")
    private String position;

    @Schema(description = "ID корпуса", example = "1")
    private Long buildingId;
}
