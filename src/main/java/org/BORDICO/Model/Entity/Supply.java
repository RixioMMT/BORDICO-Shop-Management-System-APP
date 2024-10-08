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
@Table(name = "supplies")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "supply_name", nullable = false, length = 100)
    private String supplyName;
    @Column(name = "supply_price")
    private BigDecimal supplyPrice;
    @Column(name = "supply_quantity", nullable = false)
    private Integer supplyQuantity;
    @Column(name = "supply_is_yarn", nullable = false)
    private Boolean supplyIsYarn;
    @Column(name = "yarn_grams")
    private Double yarnGrams;
    @Column(name = "supply_brand", nullable = false, length = 100)
    private String supplyBrand;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToMany(mappedBy = "supplies")
    private Set<OutcomeOrder> outcomeOrders;
}