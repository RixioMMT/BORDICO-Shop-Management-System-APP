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
@Table(name = "outcomes")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Outcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_reference", nullable = false, unique = true, length = 100)
    private String orderReference;
    @Column(name = "order_number", length = 100)
    private String orderNumber;
    @Column(name = "order_place", nullable = false, length = 100)
    private String orderPlace;
    @Column(name = "outcome_price", nullable = false)
    private BigDecimal orderPrice;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToOne(mappedBy = "outcome", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private OutcomeOrder outcomeOrder;
}
