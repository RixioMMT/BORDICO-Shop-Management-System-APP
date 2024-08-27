package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.BORDICO.Model.Enum.IncomePlatform;
import org.BORDICO.Model.Enum.PaymentMethod;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "incomes")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "income_name",length = 100)
    private String incomeName;
    @Column(name = "income_description",length = 200)
    private String incomeDescription;
    @Column(name = "income_price", nullable = false)
    private BigDecimal incomePrice;
    @Column(name = "order_reference", nullable = false, unique = true, length = 100)
    private String orderReference;
    @Enumerated(EnumType.STRING)
    @Column(name = "income_platform", nullable = false)
    private IncomePlatform incomePlatform;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToOne(mappedBy = "income", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private IncomeOrder IncomeOrder;
}
