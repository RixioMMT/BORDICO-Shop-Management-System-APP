package org.BORDICO.Controllers;

import graphql.GraphQLException;
import lombok.RequiredArgsConstructor;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Inputs.UserInput;
import org.BORDICO.Resolver.UserResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserResolver userResolver;
    @MutationMapping
    public User addUser(@Argument UserInput userInput) throws GraphQLException {
        return userResolver.addUser(userInput);
    }
}
