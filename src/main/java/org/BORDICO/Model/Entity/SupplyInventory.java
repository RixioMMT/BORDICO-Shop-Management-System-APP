package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "supply_inventory")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SupplyInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "supply_sku_code", nullable = false, length = 100)
    private String supplySkuCode;
    @Column(name = "supply_name", nullable = false, length = 100)
    private String supplyName;
    @Column(name = "supply_quantity", nullable = false)
    private double supplyQuantity;
    @Column(name = "supply_pack_price")
    private BigDecimal supplyPackPrice;
    @Column(name = "supply_is_yarn", nullable = false)
    private boolean supplyIsYarn;
    @Column(name = "yarn_grams")
    private double yarnGrams;
    @Column(name = "supply_brand", nullable = false, length = 100)
    private String supplyBrand;
    @Column(name = "order_reference", length = 100)
    private String orderReference;
    @Column(name = "product_reference", length = 100)
    private String productReference;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outcome_order_id", nullable = false)
    private OutcomeOrder outcomeOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_inventory_id", nullable = false)
    private ProductInventory productInventory;
}