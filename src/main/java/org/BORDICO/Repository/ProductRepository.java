package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductName(String productName);
    @Query("SELECT p.productPrice FROM Product p WHERE p.productName = :productName")
    BigDecimal findProductPriceByProductName(@Param("productName") String productName);
}
