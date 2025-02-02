package com.bgituit.deskmonitor.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "computers")
public class Computer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "computer_id_seq")
    @SequenceGenerator(name = "computer_id_seq", sequenceName = "computer_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "serialNumber", nullable = false, unique = true)
    private String serialNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auditorium_id", referencedColumnName = "id")
    private Auditorium auditorium;

    @Column(name = "position", nullable = false, unique = true)
    private String position;
}
