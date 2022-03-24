package com.zipbom.zipbom.Product.dto;

import com.zipbom.zipbom.Product.model.ProductType;
import com.zipbom.zipbom.Product.model.TradeType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilterRequest {
	private double upperLatitude;
	private double upperLongitude;
	private double lowerLatitude;
	private double lowerLongitude;
	private long upperPrice;
	private long lowerPrice;
	private TradeType tradeType;
	private ProductType productType;
	private int upperMaintenanceFees;
	private int lowerMaintenanceFees;
	private int numberOfRooms;
	private int livingFloor;
	private boolean canPet;
	private boolean haveElevator;
	private boolean haveParkingLot;
	private boolean isNewBuilding;
}
