package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.IncomeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeOrderRepository extends JpaRepository<IncomeOrder, Long> {
}
