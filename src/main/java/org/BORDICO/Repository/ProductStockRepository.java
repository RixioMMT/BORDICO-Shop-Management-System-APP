package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
}
