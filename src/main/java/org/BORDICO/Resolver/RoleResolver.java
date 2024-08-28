package org.BORDICO.Resolver;

import graphql.GraphQLException;
import lombok.RequiredArgsConstructor;
import org.BORDICO.Model.Entity.Role;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Inputs.RoleInput;
import org.BORDICO.Model.Inputs.UserInput;
import org.BORDICO.Service.RoleService;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RoleResolver {
    private final RoleService roleService;
    public Role addRole(RoleInput roleInput) throws GraphQLException {
        return roleService.addRole(roleInput);
    }
}
