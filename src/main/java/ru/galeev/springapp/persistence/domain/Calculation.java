package ru.galeev.springapp.persistence.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "calculation")
public class Calculation {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "active")
    private boolean active;

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
    @JoinColumn(name = "tech_carrier_wall")
    private Integer techCarrierWall; // Y координата геопозиции
    @Setter
    @JoinColumn(name = "tech_exterior")
    private Integer techExterior; // Y координата геопозиции
    @Setter
    @JoinColumn(name = "tech_foundation")
    private Integer techFoundation; // Y координата геопозиции
    @Setter
    @JoinColumn(name = "tech_overlaps")
    private Integer techOverlaps; // Y координата геопозиции
    @Setter
    @JoinColumn(name = "tech_roof")
    private Integer techRoof; // Y координата геопозиции
    @Setter
    @JoinColumn(name = "tech_ladder")
    private Integer techLadder; // Y координата геопозиции
    @Setter
    @JoinColumn(name = "tech_interior")
    private Integer techInterior; // Y координата геопозиции

    @Setter
    @Column(name = "tech_partitions")
    private Integer techPartitions; // Y координата геопозиции
    @Setter
    @Column(name = "tech_window")
    private Integer techWindow; // Y координата геопозиции
}
