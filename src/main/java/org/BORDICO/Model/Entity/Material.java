package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "materials")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "supply_name", nullable = false, length = 100)
    private String supplyName;
    @Column(name = "supply_is_yarn", nullable = false)
    private Boolean supplyIsYarn;
    @Column(name = "yarn_grams")
    private Double yarnGrams;
    @ManyToMany(mappedBy = "materials")
    private Set<Pattern> patterns;
}