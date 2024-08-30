package org.BORDICO.Service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.Entity.Cart;
import org.BORDICO.Model.Entity.Role;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Enum.RolePosition;
import org.BORDICO.Model.Inputs.LogInInput;
import org.BORDICO.Model.Inputs.UserInput;
import org.BORDICO.Repository.CartRepository;
import org.BORDICO.Repository.RoleRepository;
import org.BORDICO.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Builder
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final CartRepository cartRepository;
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
        if (userInput.getEmail().isBlank() || userInput.getPhone().isBlank() || userInput.getPassword().isBlank()) {
            throw new CustomException("Empty values are not allowed");
        }
        String phoneInput = userInput.getPhone().replaceAll("\\D", "");
        if (phoneInput.isBlank()) {
            throw new CustomException("Phone number must contain at least one digit");
        }
        if (userRepository.findByEmail(userInput.getEmail()) != null) {
            throw new CustomException("Email is already used: " + userInput.getEmail());
        }
        Set<Role> roles = new HashSet<>();
        for (RolePosition rolePosition : userInput.getRolePositions()) {
            Role role = roleRepository.findByRolePosition(rolePosition);
            if (role == null) {
                throw new CustomException("Role not found: " + rolePosition);
            }
            roles.add(role);
        }
        User user = User.builder()
                .email(userInput.getEmail())
                .phone(phoneInput)
                .password(passwordEncoder.encode(userInput.getPassword()))
                .firstName(userInput.getFirstName())
                .lastName(userInput.getLastName())
                .roles(roles)
                .build();
        user = userRepository.save(user);
        Set<Cart> carts = new HashSet<>();
        Cart cart = Cart.builder()
                .user(user)
                .build();
        cart = cartRepository.save(cart);
        carts.add(cart);
        user.setCarts(carts);
        return userRepository.save(user);
    }
}
