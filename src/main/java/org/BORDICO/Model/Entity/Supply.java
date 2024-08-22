package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.BORDICO.Model.Enum.Unit;

@Entity
@Table(name = "supplies")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Enumerated(EnumType.STRING)
    @Column(name = "unit", nullable = false, length = 50)
    private Unit unit;
    @Column(name = "brand", length = 100)
    private String brand;
    @Column(name = "description", length = 500)
    private String description;
    @OneToOne(mappedBy = "supply", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Material material;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pattern_id")
    private Pattern pattern;
}