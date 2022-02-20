package com.zipbom.zipbom.Product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilterRequest {
	private double upperLatitude;
	private double upperLongitude;
	private double lowerLatitude;
	private double lowerLongitude;
}
