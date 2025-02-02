package com.bgituit.deskmonitor.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на изменение информации об уведомлении")
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    @Schema(description = "ID поломки", example = "44")
    private Long breakdown;

    @Schema(description = "ID пользователя, которому отправлено", example = "23")
    private Long user;

    @Schema(description = "Просмотрено ли", example = "true")
    private Boolean isViewed;
}
