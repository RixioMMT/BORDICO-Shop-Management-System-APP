package org.BORDICO.Resolver;

import graphql.GraphQLException;
import lombok.RequiredArgsConstructor;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Inputs.UserInput;
import org.BORDICO.Service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@RequiredArgsConstructor
public class UserResolver {
    private final UserService userService;
    //@PreAuthorize("isAuthenticated()")
    @PreAuthorize("hasAuthority('CLIENT')")
    public User addUser(UserInput userInput) throws GraphQLException {
        return userService.addUser(userInput);
    }
}
