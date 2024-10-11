package org.BORDICO.Service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Inputs.LogInInput;
import org.BORDICO.Model.Inputs.UserInput;
import org.BORDICO.Repository.CartRepository;
import org.BORDICO.Repository.RoleRepository;
import org.BORDICO.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final CartRepository cartRepository;
    private final UserService userService;
    private final CartService cartService;
    private final NotificationService notificationService;
    public User logIn(LogInInput logInInput) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        logInInput.getEmail(),
                        logInInput.getPassword()
                )
        );
        return userRepository.findByEmail(logInInput.getEmail());
    }
    public User signUp(UserInput userInput) throws CustomException {
        User user = new User();
        user = userService.getUserFromInput(userInput, user);
        user = cartService.createCartForUser(user);
        return notificationService.createUserNotification(user);
    }
}
