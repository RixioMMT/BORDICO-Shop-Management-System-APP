package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.BORDICO.Model.Enum.CartStatus;
import org.BORDICO.Model.Enum.PaymentMethod;
import org.BORDICO.Model.Enum.PaymentStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_name", length = 100)
    private String clientName;
    @Column(name = "bank", length = 100)
    private String bank;
    @Column(name = "confirmation_number", length = 100)
    private String confirmationNumber;
    @Column(name = "payment_price")
    private Double paymentPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;
    @Column(name = "confirmation_date")
    private LocalDateTime confirmationDate;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cart cart;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_order_id")
    private IncomeOrder incomeOrder;
    @PrePersist
    public void prePersist() {
        if (paymentStatus == null) {
            paymentStatus = PaymentStatus.PENDING;
        }
    }
}
