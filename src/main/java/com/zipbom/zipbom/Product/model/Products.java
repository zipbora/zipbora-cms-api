package com.zipbom.zipbom.Product.model;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Products {

    @OneToMany(mappedBy = "user")
    private final List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        productList.add(product);
    }

    public List<Product> getProductList() {
        return productList;
    }
}
