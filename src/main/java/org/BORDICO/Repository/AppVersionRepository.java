package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.AppVersion;
import org.BORDICO.Model.Enum.OS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppVersionRepository extends JpaRepository<AppVersion, OS> {
}
