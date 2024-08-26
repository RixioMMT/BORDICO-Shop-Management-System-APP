package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "materials")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "supply_sku_code", nullable = false, length = 100)
    private String supplySkuCode;
    @Column(name = "supply_name", nullable = false, length = 100)
    private String supplyName;
    @Column(name = "supply_is_yarn", nullable = false)
    private boolean supplyIsYarn;
    @Column(name = "yarn_grams")
    private double yarnGrams;
    @Column(name = "supply_brand", nullable = false, length = 100)
    private String supplyBrand;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pattern_id", nullable = false)
    private Pattern pattern;
}