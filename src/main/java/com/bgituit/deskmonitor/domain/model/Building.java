package com.bgituit.deskmonitor.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "buildings")
public class Building {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_id_seq")
    @SequenceGenerator(name = "building_id_seq", sequenceName = "building_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "number", nullable = false, unique = true)
    private long number;
}
