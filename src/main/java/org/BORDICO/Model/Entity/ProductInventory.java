package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_inventory")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ProductInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "item_name", nullable = false, length = 100)
    private String itemName;
    @Column(name = "item_color_type", nullable = false, length = 200)
    private String itemColorType;
    @Column(name = "is_Sold",  nullable = false)
    private Boolean isSold;
    @Column(name = "manufactured_date", nullable = false)
    private LocalDateTime manufacturedDate;
    @Column(name = "sold_at")
    private LocalDateTime soldAt;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_order_id")
    private IncomeOrder incomeOrder;
}
