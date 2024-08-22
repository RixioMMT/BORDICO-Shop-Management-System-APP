package org.BORDICO.Repository;

import org.BORDICO.Model.Enum.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
