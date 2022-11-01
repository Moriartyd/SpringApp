package ru.akimov.springapp.persistence.domain;

import lombok.Getter;
import lombok.Setter;
import ru.akimov.springapp.persistence.domain.tech.*;
import ru.akimov.springapp.persistence.domain.user.User;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "calculation")
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Column(name = "active")
    private boolean active;

    @Setter
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @Column(name = "cost")
    private Integer cost; // X координата геопозиции
    @Setter
    @Column(name = "width")
    private double width; // Y координата геопозиции
    @Setter
    @Column(name = "height")
    private double height; // Y координата геопозиции
    @Setter
    @Column(name = "length")
    private double length; // Y координата геопозиции
    @Setter
    @Column(name = "floors")
    private Integer floors; // X координата геопозиции

    @Setter
    @OneToOne
    @JoinColumn(name = "tech_carrier_wall")
    private TechCarrierWall techCarrierWall; // Y координата геопозиции
    @Setter
    @OneToOne
    @JoinColumn(name = "tech_exterior")
    private TechExterior techExterior; // Y координата геопозиции
    @Setter
    @OneToOne
    @JoinColumn(name = "tech_foundation")
    private TechFoundation techFoundation; // Y координата геопозиции
    @Setter
    @OneToOne
    @JoinColumn(name = "tech_overlaps")
    private TechOverlaps techOverlaps; // Y координата геопозиции
    @Setter
    @OneToOne
    @JoinColumn(name = "tech_roof")
    private TechRoof techRoof; // Y координата геопозиции
    @Setter
    @OneToOne
    @JoinColumn(name = "tech_ladder")
    private TechLadder techLadder; // Y координата геопозиции
    @Setter
    @OneToOne
    @JoinColumn(name = "tech_interior")
    private TechInterior techInterior; // Y координата геопозиции

    @Setter
    @Column(name = "tech_partitions")
    private Integer techPartitions; // Y координата геопозиции
    @Setter
    @Column(name = "tech_window")
    private Integer techWindow; // Y координата геопозиции
}
