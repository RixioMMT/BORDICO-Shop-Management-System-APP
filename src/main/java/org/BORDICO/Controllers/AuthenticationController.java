package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.ExceptionGraphql;
import org.BORDICO.Model.Inputs.LogInInput;
import org.BORDICO.Model.Inputs.UserInput;
import org.BORDICO.Resolver.AuthenticationResolver;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationResolver authenticationResolver;
    @MutationMapping
    public String logIn(@Argument LogInInput logInInput) {
        return authenticationResolver.logIn(logInInput);
    }
    @MutationMapping
    public String signUp(@Argument UserInput userInput) throws ExceptionGraphql {
        return authenticationResolver.signUp(userInput);
    }
}
