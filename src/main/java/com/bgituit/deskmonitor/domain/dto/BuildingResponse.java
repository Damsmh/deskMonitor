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
public class BuildingResponse {
    @Schema(description = "Список корпусов", example = "...")
    private List<BuildingResponse> buildings;
}
