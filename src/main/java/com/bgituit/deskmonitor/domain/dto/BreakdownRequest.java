package com.bgituit.deskmonitor.domain.dto;

import com.bgituit.deskmonitor.domain.model.Computer;
import com.bgituit.deskmonitor.domain.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Schema(description = "Запрос на изменение информации о поломке")
@AllArgsConstructor
@NoArgsConstructor
public class BreakdownRequest {
    @Schema(description = "ID компьютера", example = "322")
    private Long computer;

    @Schema(description = "Уровень поломки (1 - 3)", example = "2")
    private Integer level;

    @Schema(description = "ID отправившего пользователя", example = "275")
    private Long user;

    @Schema(description = "Решена", example = "false")
    private Boolean isSolved;

    @Schema(description = "Описание", example = "Неисправна видеокарта")
    private String description;

    @Schema(description = "Дата в виде timestamp", example = "182769548297")
    private Date date;
}
