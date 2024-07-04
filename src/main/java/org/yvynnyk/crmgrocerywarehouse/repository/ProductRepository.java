package org.yvynnyk.crmgrocerywarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.yvynnyk.crmgrocerywarehouse.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findAllById(@NonNull Long id);

	@Query("""
			SELECT p FROM Product p WHERE p.name LIKE %:name%
			AND (:minPrice IS NULL OR p.price >= :minPrice)
			AND (:maxPrice IS NULL OR p.price <= :maxPrice)
			""")
	List<Product> findByNameAndPriceRange(@Param("name") String name,
										  @Param("minPrice") Double minPrice,
										  @Param("maxPrice") Double maxPrice);
}