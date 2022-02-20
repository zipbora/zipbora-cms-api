package com.zipbom.zipbom.Product.dto;

import com.zipbom.zipbom.Product.model.Product;
import com.zipbom.zipbom.Product.model.ProductType;

import lombok.Getter;

@Getter
public class ProductResponse {
    private Long id;
    private ProductType productType;
    private int numberOfRooms;
    private int maintenanceFees;
    private float size;
    private String thumbnail;
    private long price;
    private double latitude;
    private double longitude;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.productType = product.getProductType();
        this.numberOfRooms = product.getNumberOfRooms();
        this.maintenanceFees = product.getMaintenanceFees();
        this.size = product.getSize();
        this.thumbnail = product.getThumbnail();
        this.price = product.getPrice();
        this.latitude = product.getLatitude();
        this.longitude = product.getLongitude();
    }
}
