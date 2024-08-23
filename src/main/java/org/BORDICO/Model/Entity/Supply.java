package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "supplies")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true, length = 200)
    private String name;
    @Column(name = "yarn_grams")
    private double yarnGrams;
    @Column(name = "brand", length = 100)
    private String brand;
    @Column(name = "description", length = 500)
    private String description;
    @Column(name = "is_yarn", nullable = false)
    private boolean isYarn;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;
    @OneToOne(mappedBy = "supply", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Material material;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outcome_id", nullable = false)
    private Outcome outcome;
}