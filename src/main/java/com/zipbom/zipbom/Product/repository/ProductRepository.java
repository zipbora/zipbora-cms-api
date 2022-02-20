package com.zipbom.zipbom.Product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zipbom.zipbom.Product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value = "select p from Product as p where p.id = :id")
	Optional<Product> findByProductId(@Param("id") Long id);

	@Query(value = "select p from Product as p where (p.latitude between :lowerLatitude and :upperLatitude) "
		+ "and (p.longitude between :lowerLongitude and :upperLongitude)")
	List<Product> findByLatitudeAndLongitude(@Param("upperLatitude") double upperLatitude,
		@Param("upperLongitude") double upperLongitude, @Param("lowerLatitude") double lowerLatitude
		, @Param("lowerLongitude") double lowerLongitude);

}
