package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
