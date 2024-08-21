package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}
