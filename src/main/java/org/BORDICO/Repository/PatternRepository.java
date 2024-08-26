package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatternRepository extends JpaRepository<Pattern, Long> {
}
