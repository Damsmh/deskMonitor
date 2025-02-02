package com.bgituit.deskmonitor.domain.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ c информацией о всех уведомлениях")
public class NotificationResponse {
    @Schema(description = "Список уведомлений", example = "...")
    private List<NotificationResponse> notifications;
}
