package com.bgituit.deskmonitor.domain.model.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ComputerResponseModel {
    @Schema(description = "ID компьютера", example = "33")
    private Long id;

    @Schema(description = "SN компьютера", example = "123")
    private String serialNumber;

    @Schema(description = "ID аудитории", example = "54")
    private Long auditoriumId;

    @Schema(description = "Позиция", example = "125;123")
    private String position;

    @Schema(description = "Размер", example = "125*125")
    private String size;

}
