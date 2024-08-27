package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "product_inventory")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class ProductInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;
    @Column(name = "product_sku_code", nullable = false, length = 100)
    private String productSkuCode;
    @Column(name = "product_reference", nullable = false, length = 100)
    private String productReference;
    @Column(name = "product_price", nullable = false)
    private BigDecimal productPrice;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "productInventory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SupplyInventory> supplies;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_stock_id", nullable = false)
    private ProductStock productStock;
}
