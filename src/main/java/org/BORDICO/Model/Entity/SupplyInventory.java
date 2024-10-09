package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Entity
@Table(name = "supply_inventory")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SupplyInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "supply_name", nullable = false, length = 100)
    private String supplyName;
    @Column(name = "supply_price")
    private BigDecimal supplyPrice;
    @Column(name = "supply_is_yarn", nullable = false)
    private Boolean supplyIsYarn;
    @Column(name = "yarn_grams")
    private Double yarnGrams;
    @Column(name = "supply_brand", nullable = false, length = 100)
    private String supplyBrand;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outcome_order_id", nullable = false)
    private OutcomeOrder outcomeOrder;
}
