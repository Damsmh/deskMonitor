package com.bgituit.deskmonitor.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auditoriums")
public class Auditorium {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Schema(description = "Название аудитории", example = "Аудитория 314")
    private String name;

    @Column(name = "floor", nullable = false)
    @Schema(description = "Этаж", example = "3")
    private Integer floor;

    @Column(name = "isComputer", nullable = false)
    @Schema(description = "Тип аудитории", example = "true")
    private Boolean isComputer;

    @Column(name = "size", nullable = false)
    @Schema(description = "Размер", example = "300*400")
    private String size;

    @Column(name = "position", nullable = false)
    @Schema(description = "Позиция", example = "300;400")
    private String position;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "building_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Building building;
}
