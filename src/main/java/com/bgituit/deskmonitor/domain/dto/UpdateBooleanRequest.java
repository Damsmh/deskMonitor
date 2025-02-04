package com.bgituit.deskmonitor.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на изменение статуса ошибки/уведомления")
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBooleanRequest {
    @Schema(description = "ID ошибки/уведомления", example = "22")
    private Long id;

    @Schema(description = "Булевый параметр", example = "true")
    private Boolean boolValue;
}
