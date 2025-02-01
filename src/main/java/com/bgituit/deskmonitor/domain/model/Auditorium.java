package com.bgituit.deskmonitor.domain.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
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

    @ManyToOne
    private Building building;

}
