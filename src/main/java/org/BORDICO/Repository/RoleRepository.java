package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.Role;
import org.BORDICO.Model.Enum.RolePosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRolePosition(RolePosition rolePosition);
}
