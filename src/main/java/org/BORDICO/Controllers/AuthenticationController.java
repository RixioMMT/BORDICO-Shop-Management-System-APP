package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Inputs.LogInInput;
import org.BORDICO.Model.Inputs.UserInput;
import org.BORDICO.Service.AuthenticationService;
import org.BORDICO.Service.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JWTService jwtService;
    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LogInInput logInInput) {
        User authenticatedUser = authenticationService.logIn(logInInput);
        String token = jwtService.generateToken(authenticatedUser);
        return ResponseEntity.ok(token);
    }
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserInput userInput) throws CustomException {
        User user = authenticationService.signUp(userInput);
        LogInInput logInInput = new LogInInput();
        logInInput.setEmail(user.getEmail());
        logInInput.setPassword(userInput.getPassword());
        User authenticatedUser = authenticationService.logIn(logInInput);
        String token = jwtService.generateToken(authenticatedUser);
        return ResponseEntity.ok(token);
    }
}