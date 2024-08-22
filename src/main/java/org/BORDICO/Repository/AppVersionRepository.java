package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {
}
