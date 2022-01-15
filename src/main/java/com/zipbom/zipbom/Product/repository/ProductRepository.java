package com.zipbom.zipbom.Product.repository;

import com.zipbom.zipbom.Product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select p from Product as p where p.id = :id")
    Optional<Product> findByProductId(@Param("id") Long id);

}
