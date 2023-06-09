package com.interpackage.tracking.model;

import com.interpackage.tracking.pk.PKTracking;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tracking")
@IdClass(PKTracking.class)
public class Tracking {

    @Id
    @Column(name = "id_cellar", nullable = false)
    private Long idCellar;

    @Id
    @Column(name = "id_journey", nullable = false)
    private Long idJourney;

    @Id
    @Column(columnDefinition = "TEXT", name = "id_order", nullable = false)
    private String idOrder;

    @Id
    @Column(nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date;

    @Column(nullable = false)
    private Boolean entered;

    @Column(name = "partial_cost", scale = 3)
    private Float partialCost;

    @Column(nullable = false, length = 75)
    private String journey;

    @Column(name = "photo_base64")
    private String photoBase64;

    @Column(nullable = false, length = 75)
    private String city;

    @Column(length = 500)
    private String comment;
}
