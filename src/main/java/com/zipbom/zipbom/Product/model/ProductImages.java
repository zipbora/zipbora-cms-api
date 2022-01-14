package com.zipbom.zipbom.Product.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

public class ProductImages {

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_image_id")
    private List<ProductImage> productImages;

    private ProductImages() {
    }

    public ProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public void add(ProductImage productImage) {
        productImages.add(productImage);
    }
}
