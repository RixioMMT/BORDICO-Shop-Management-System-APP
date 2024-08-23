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
    @Column(name = "quantity")
    private double quantity;
    @Column(name = "yarn_grams")
    private double yarnGrams;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supply_id", nullable = false)
    private Supply supply;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pattern_id", nullable = false)
    private Pattern pattern;
}