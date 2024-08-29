package org.BORDICO.Resolver;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.ExceptionGraphql;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Inputs.LogInInput;
import org.BORDICO.Model.Inputs.UserInput;
import org.BORDICO.Service.AuthenticationService;
import org.BORDICO.Service.JWTService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationResolver {
    private final AuthenticationService authenticationService;
    private final JWTService jwtService;
    public String logIn(LogInInput logInInput) {
        User authenticatedUser = authenticationService.logIn(logInInput);
        return jwtService.generateToken(authenticatedUser);
    }
    public String signUp(UserInput userInput) throws ExceptionGraphql {
        User user = authenticationService.signUp(userInput);
        LogInInput logInInput = new LogInInput();
        logInInput.setEmail(user.getEmail());
        logInInput.setPassword(user.getPassword());
        User authenticatedUser = authenticationService.logIn(logInInput);
        return jwtService.generateToken(authenticatedUser);
    }
}
