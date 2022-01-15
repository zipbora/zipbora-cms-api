package com.zipbom.zipbom.Product.model;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class ProductImages {

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductImage> productImages = new ArrayList<>();

    public ProductImages() {
    }

    public ProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public void add(ProductImage productImage) {
        productImages.add(productImage);
    }
}
