package org.BORDICO.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "inventory")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name", nullable = false, length = 200)
    private String productName;
    @Column(name = "product_color_type", nullable = false, length = 200)
    private int productColorType;
    @Column(name = "product_image_url")
    private String productImageUrl;
    @Column(name = "is_Sold",  nullable = false)
    private Boolean isSold;
    @Column(name = "manufactured_at", nullable = false)
    private LocalDateTime manufacturedAt;
    @Column(name = "sold_at",  nullable = false)
    private LocalDateTime soldAt;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
