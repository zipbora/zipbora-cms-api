package com.zipbom.zipbom.Product.model;

import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.InterestedRoom.model.ProductInterestedRooms;
import com.zipbom.zipbom.RecentView.model.ProductRecentViews;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String thumbnail;

    @Embedded
    private ProductRecentViews recentViews;

    @Embedded
    private ProductInterestedRooms interestedRooms;

    @Embedded
    private ProductImages productImages;
    private long price;
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
    private double latitude;
    private double longitude;
    private boolean isNewBuilding;

    protected Product() {
    }

    public Product(User user) {
        this.user = user;
    }

    public void addProduct(ProductImage productImage) {
        productImages.add(productImage);
    }

}
