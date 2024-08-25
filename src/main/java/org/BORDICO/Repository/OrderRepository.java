package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.IncomeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<IncomeOrder, Long> {
}
