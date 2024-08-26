package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {
}
