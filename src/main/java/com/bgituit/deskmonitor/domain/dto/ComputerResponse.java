package com.bgituit.deskmonitor.domain.dto;

import com.bgituit.deskmonitor.domain.model.Response.ComputerResponseModel;
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
@Schema(description = "Ответ c информацией о всех компьютерах")
public class ComputerResponse {
    @Schema(description = "Список компьютеров")
    private List<ComputerResponseModel> computers;
}
