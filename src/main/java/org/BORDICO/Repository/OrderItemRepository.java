package org.BORDICO.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<IncomeOrderItem, Long> {
}
