package com.bgituit.deskmonitor.domain.dto;

import com.bgituit.deskmonitor.domain.model.Response.AuditoriumResponseModel;
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
@Schema(description = "Ответ c информацией о всех аудиториях")
public class AuditoriumResponse {
    @Schema(description = "Список аудиторий")
    private List<AuditoriumResponseModel> auditoriums;
}