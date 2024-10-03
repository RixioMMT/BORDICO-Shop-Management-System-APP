package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyInventoryRepository extends JpaRepository<Supply, Long> {
}
