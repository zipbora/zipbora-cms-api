package com.zipbom.zipbom.Auth.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private ProductType productType;

    private String address;

    private String detailAddress;

    private float size;

    private TradeType tradeType;

    private int maintenanceFees;

    private boolean haveLoan;

    private LocalDate moveInDate;

    private int numberOfBathrooms;

    private int numberOfRooms;

    private int totalNumberOfFloor;

    private int livingFloor;

    private boolean isLiving;

    private boolean canPet;

    private LocalDate constructionYear;

    private boolean haveElevator;

    private boolean haveParkingLot;

    private String detailExplanation;

    private LocalDateTime availableTime;

    private boolean isAgent;
}