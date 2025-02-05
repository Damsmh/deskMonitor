package com.bgituit.deskmonitor.domain.model.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Builder
@Data
public class BreakdownResponseModel {
    @Schema(description = "ID поломки", example = "12")
    private Long id;

    @Schema(description = "Уровень поломки", example = "2")
    private Integer level;

    @Schema(description = "Решена ли", example = "true")
    private Boolean isSolved;

    @Schema(description = "Описание", example = "Неисправна видеокарта")
    private String description;

    @Schema(description = "Дата", example = "...")
    private Date date;

    @Schema(description = "ID компьютера", example = "33")
    private Long computerId;

    @Schema(description = "ID отправившего пользователя", example = "23")
    private Long userId;
}
