package com.bgituit.deskmonitor.domain.dto;

import com.bgituit.deskmonitor.domain.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ c информацией о пользователе")
public class ProfileResponse {
    @Schema(description = "Имя пользователя", example = "Jon")
    private String username;

    @Schema(description = "Почта", example = "jon@gmail.com")
    private String email;

    @Schema(description = "Роль", example = "ROLE_ADMIN")
    private Role role;
}
