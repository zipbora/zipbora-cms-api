package com.zipbom.zipbom.Product.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProductType {
    APARTMENT("아파트")
    , officetel("오피스텔"), villa("빌라"), store("상가");
    private final String type;
}


