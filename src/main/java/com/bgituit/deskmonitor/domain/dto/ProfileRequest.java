package com.bgituit.deskmonitor.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос изменения информации о пользователе")
public class ProfileRequest {
    @Schema(description = "Имя пользователя", example = "Jon")
    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String username;

    @Schema(description = "Почта", example = "jon@gmail.com")
    @Size(max = 255, message = "Длина почты должна быть до 255 символов")
    private String email;

    @Schema(description = "Пароль", example = "passwordexample")
    @Size(max = 255, message = "Длина пароля должна быть до 255 символов")
    private String password;
}
