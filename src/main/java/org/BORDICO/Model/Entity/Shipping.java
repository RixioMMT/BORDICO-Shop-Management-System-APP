package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.BORDICO.Model.Enum.ShippingStatus;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ShippingStatus shippingStatus;
    @Column(name = "shipping_date", nullable = false)
    private LocalDateTime shippingDate;
    @OneToOne(mappedBy = "shipping", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Order order;
}
