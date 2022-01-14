package com.zipbom.zipbom.Product.dto;

import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Product.model.ProductType;
import com.zipbom.zipbom.Product.model.TradeType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LetRoomRequestDto {

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
