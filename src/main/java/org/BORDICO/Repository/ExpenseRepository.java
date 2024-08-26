package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
