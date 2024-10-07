package org.BORDICO.Service;

import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.Entity.Role;
import org.BORDICO.Model.Enum.RolePosition;
import org.BORDICO.Model.Inputs.RoleInput;
import org.BORDICO.Repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    @PostConstruct
    public void initializeRoles() {
        initializeRole(RolePosition.CLIENT);
        initializeRole(RolePosition.ADMIN);
        initializeRole(RolePosition.CEO);
    }
    private void initializeRole(RolePosition rolePosition) {
        if (roleRepository.findByRolePosition(rolePosition) != null) {
            throw new CustomException(rolePosition + " already exists.");
        }
        Role role = new Role();
        role.setRolePosition(rolePosition);
        roleRepository.save(role);
    }
}
