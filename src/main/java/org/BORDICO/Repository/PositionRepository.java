package org.BORDICO.Repository;

import org.BORDICO.Model.Enum.RolePosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<RolePosition, Long> {
}
