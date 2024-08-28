package org.BORDICO.Controllers;

import graphql.GraphQLException;
import lombok.RequiredArgsConstructor;
import org.BORDICO.Model.Entity.Role;
import org.BORDICO.Model.Inputs.RoleInput;
import org.BORDICO.Resolver.RoleResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RoleController {
    private final RoleResolver roleResolver;
    @MutationMapping
    public Role addRole(@Argument RoleInput roleInput) throws GraphQLException {
        return roleResolver.addRole(roleInput);
    }
}
