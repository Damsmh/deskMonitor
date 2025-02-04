package com.bgituit.deskmonitor.domain.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;



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

    @Column(name = "number", nullable = false)
    @Schema(description = "Номер аудитории", example = "314")
    private Integer number;

    @Column(name = "floor", nullable = false)
    @Schema(description = "Этаж", example = "3")
    private Integer floor;

    @Column(name = "size", nullable = false)
    @Schema(description = "Размер", example = "300*400")
    private String size;

    @Column(name = "position", nullable = false)
    @Schema(description = "Позиция", example = "300;400")
    private String position;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    @Schema(description = "ID корпуса", example = "1")
    private Building building;

}
