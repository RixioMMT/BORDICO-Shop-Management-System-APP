package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.SupplyInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<SupplyInventory, Long> {
}
