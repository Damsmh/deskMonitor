package com.bgituit.deskmonitor.domain.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auditorium_id_seq")
    @SequenceGenerator(name = "auditorium_id_seq", sequenceName = "auditorium_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private Integer number;

    @Column(name = "floor", nullable = false)
    private Integer floor;

    @Column(name = "size", nullable = false, unique = true)
    private String size;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;

}
