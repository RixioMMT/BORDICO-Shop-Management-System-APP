package org.BORDICO.Service;

import graphql.GraphQLException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.BORDICO.Model.Entity.Role;
import org.BORDICO.Model.Inputs.RoleInput;
import org.BORDICO.Repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role addRole(RoleInput roleInput) throws GraphQLException {
        Role role = new Role();
        role.setRolePosition(roleInput.getRolePosition());
        return roleRepository.save(role);
    }
}
