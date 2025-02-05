package com.bgituit.deskmonitor.domain.model.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NotificationResponseModel {
    @JsonProperty("id")
    @Schema(description = "ID уведомления", example = "1")
    private Long id;

    @JsonProperty("isViewed")
    @Schema(description = "Просмотрено ли", example = "true")
    private Boolean isViewed;

    @JsonProperty("breakdownId")
    @Schema(description = "ID поломки", example = "2")
    private Long breakdownId;

    @JsonProperty("userId")
    @Schema(description = "ID пользователя", example = "1")
    private Long userId;
}
