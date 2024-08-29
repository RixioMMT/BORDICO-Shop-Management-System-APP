package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "expense_name", nullable = false, unique = true, length = 100)
    private String expenseName;
    @Column(name = "expense_description", nullable = false, length = 200)
    private String expenseDescription;
    @Column(name = "expense_price", nullable = false)
    private BigDecimal expensePrice;
    @Column(name = "is_supply", nullable = false)
    private boolean isSupply;
    @Column(name = "supply_brand", length = 100)
    private String supplyBrand;
    @Column(name = "supply_sku_code", unique = true, length = 100)
    private String supplySkuCode;
    @Column(name = "supply_is_yarn", nullable = false)
    private boolean supplyIsYarn;
    @Column(name = "yarn_grams")
    private double supplyYarnGrams;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}