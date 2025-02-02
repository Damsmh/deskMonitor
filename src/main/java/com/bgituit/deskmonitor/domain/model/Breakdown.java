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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "computer_id", referencedColumnName = "id")
    private Computer computer;

    @Column(name = "level")
    @Range(min = 1, max = 3)
    private Integer level;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "isSolved")
    private Boolean isSolved;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;


}
