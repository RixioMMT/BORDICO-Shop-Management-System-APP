package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "outcome_orders")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutcomeOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_reference", nullable = false, unique = true, length = 100)
    private String orderReference;
    @Column(name = "order_number", length = 100)
    private String orderNumber;
    @Column(name = "order_place", nullable = false, length = 100)
    private String orderPlace;
    @Column(name = "order_quantity", nullable = false)
    private int orderQuantity;
    @Column(name = "order_price", nullable = false)
    private BigDecimal orderPrice;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "outcome_id")
    private Outcome outcome;
    @OneToMany(mappedBy = "outcomeOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Supply> supplies;
}
