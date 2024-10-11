package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.BORDICO.Model.Enum.PaymentMethod;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "outcome_orders")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class OutcomeOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_place", nullable = false, length = 100)
    private String orderPlace;
    @Column(name = "order_price", nullable = false)
    private BigDecimal orderPrice;
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "outcome_id")
    private Outcome outcome;
    @OneToMany(mappedBy = "outcomeOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SupplyInventory> suppliesInventory;
}
