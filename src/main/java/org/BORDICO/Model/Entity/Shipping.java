package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.BORDICO.Model.Enum.ShippingStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "shippings")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "carrier",length = 100)
    private String carrier;
    @Column(name = "tracking_number",length = 100)
    private String trackingNumber;
    @Column(name = "shipping_date", nullable = false)
    private LocalDateTime shippingDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ShippingStatus shippingStatus;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToOne(mappedBy = "shipping", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private IncomeOrder incomeOrder;
}
