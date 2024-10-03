package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInventoryRepository extends JpaRepository<Inventory, Long> {
}
