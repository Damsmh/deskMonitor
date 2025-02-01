package com.bgituit.deskmonitor.domain.model;


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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_id_seq")
    @SequenceGenerator(name = "building_id_seq", sequenceName = "building_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private Integer number;

    @Column(name = "floors", nullable = false, unique = true)
    private Integer floors;
}
