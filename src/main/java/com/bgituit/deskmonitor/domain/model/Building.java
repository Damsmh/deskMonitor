package com.bgituit.deskmonitor.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buildings")
public class Building {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID корпуса", example = "12")
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    @Schema(description = "Номер корпуса", example = "12")
    private Integer number;

    @Column(name = "floors", nullable = false)
    @Schema(description = "Кол-во этажей", example = "4")
    private Integer floors;

}
