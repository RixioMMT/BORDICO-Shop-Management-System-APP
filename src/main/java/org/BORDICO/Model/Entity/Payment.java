package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.BORDICO.Model.Enum.PaymentMethod;
import org.BORDICO.Model.Enum.PaymentStatus;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "payments")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
    @Column(name = "bank", nullable = false, length = 100)
    private String bank;
    @Column(name = "confirmation_number", nullable = false, length = 100)
    private String confirmationNumber;
    @Column(name = "payment_price")
    private double paymentPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cart cart;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_order_id", nullable = false)
    private IncomeOrder incomeOrder;
}
