package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.Payment;
import org.BORDICO.Model.Enum.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentStatusRepository extends JpaRepository<Payment, PaymentStatus> {
}
