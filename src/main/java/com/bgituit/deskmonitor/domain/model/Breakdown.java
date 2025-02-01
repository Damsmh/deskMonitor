package com.bgituit.deskmonitor.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.sql.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "breakdowns")
public class Breakdown {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "breakdown_id_seq")
    @SequenceGenerator(name = "breakdown_id_seq", sequenceName = "breakdown_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Computer computer;

    @Column(name = "level")
    @Range(min = 1, max = 3)
    private int level;

    @ManyToOne
    private User user;

    @Column(name = "isSolved")
    private boolean isSolved;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;


}
