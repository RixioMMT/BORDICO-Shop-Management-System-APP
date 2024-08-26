package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "income_orders")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class IncomeOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_reference", nullable = false, unique = true, length = 1000)
    private String orderReference;
    @Column(name = "shipping_address", nullable = false, length = 1000)
    private String shippingAddress;
    @Column(name = "order_price", nullable = false)
    private double orderPrice;
    @Column(name = "order_date", nullable = false)
    private LocalDateTime oderDate;
    @OneToOne(mappedBy = "incomeOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Payment payment;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_id", nullable = false)
    private Income income;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_id", nullable = false)
    private Shipping shipping;
}
