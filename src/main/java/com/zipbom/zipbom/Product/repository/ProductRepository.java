package com.zipbom.zipbom.Product.repository;

import com.zipbom.zipbom.Product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
