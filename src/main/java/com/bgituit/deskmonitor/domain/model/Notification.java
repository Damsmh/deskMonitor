package com.bgituit.deskmonitor.domain.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notifications")
public class Notification {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id_seq")
    @SequenceGenerator(name = "notification_id_seq", sequenceName = "notification_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "breakdown_id", referencedColumnName = "id")
    private Breakdown breakdown;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "isViewed")
    private Boolean isViewed;

}
