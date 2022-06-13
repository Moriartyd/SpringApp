package ru.galeev.springapp.persistence.domain.user;

import lombok.Getter;
import lombok.Setter;
import ru.galeev.springapp.utils.Hidden;

import javax.persistence.*;

@Entity
@Table(name = "contractor")
@Getter
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
//    @Hidden
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @Column(name = "rating")
    private Double rating; // X координата геопозиции
    @Setter
    @Column(name = "rating_num")
    private Integer ratingNum; // X координата геопозиции

    @Setter
    @Column(name = "min_cost")
    private double minCost; // X координата геопозиции
    @Setter
    @Column(name = "max_cost")
    private double maxCost; // Y координата геопозиции

    @Setter
    @Column(name = "min_width")
    private double minWidth; // Y координата геопозиции
    @Setter
    @Column(name = "max_width")
    private double maxWidth; // Y координата геопозиции

    @Setter
    @Column(name = "min_height")
    private double minHeight; // Y координата геопозиции
    @Setter
    @Column(name = "max_height")
    private double maxHeight; // Y координата геопозиции

    @Setter
    @Column(name = "min_length")
    private double minLength; // Y координата геопозиции
    @Setter
    @Column(name = "max_length")
    private double maxLength; // Y координата геопозиции

    @Setter
    @Column(name = "min_floors")
    private Integer minFloors; // X координата геопозиции
    @Setter
    @Column(name = "max_floors")
    private Integer maxFloors; // Y координата геопозиции

    @Setter
    @Column(name = "tech_carrier_wall")
    private String techCarrierWall; // Y координата геопозиции
    @Setter
    @Column(name = "tech_exterior")
    private String techExterior; // Y координата геопозиции
    @Setter
    @Column(name = "tech_foundation")
    private String techFoundation; // Y координата геопозиции
    @Setter
    @Column(name = "tech_overlaps")
    private String techOverlaps; // Y координата геопозиции
    @Setter
    @Column(name = "tech_roof")
    private String techRoof; // Y координата геопозиции
    @Setter
    @Column(name = "tech_ladder")
    private String techLadder; // Y координата геопозиции
    @Setter
    @Column(name = "tech_interior")
    private String techInterior; // Y координата геопозиции

    @Setter
    @Column(name = "tech_partitions")
    private Integer techPartitions; // Y координата геопозиции
    @Setter
    @Column(name = "tech_window")
    private Integer techWindow; // Y координата геопозиции
}
