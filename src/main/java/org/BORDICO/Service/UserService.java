package org.BORDICO.Service;

import graphql.GraphQLException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Entity.Role;
import org.BORDICO.Model.Enum.RolePosition;
import org.BORDICO.Model.Inputs.UserInput;
import org.BORDICO.Repository.RoleRepository;
import org.BORDICO.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Builder
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public User addUser(UserInput userInput) throws GraphQLException {
        if (userInput.getEmail().isBlank() || userInput.getPhone().isBlank() || userInput.getPassword().isBlank()) {
            throw new GraphQLException("Empty values are not allowed");
        }
        String phoneInput = userInput.getPhone().replaceAll("\\D", "");
        if (phoneInput.isBlank()) {
            throw new GraphQLException("Phone number must contain at least one digit");
        }
        if (userRepository.findByEmail(userInput.getEmail()) != null) {
            throw new GraphQLException("Email is already used: " + userInput.getEmail());
        }
        Set<Role> roles = new HashSet<>();
        for (RolePosition rolePosition : userInput.getRolePositions()) {
            Role role = roleRepository.findByRolePosition(rolePosition);
            if (role == null) {
                throw new GraphQLException("Role not found: " + rolePosition);
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
        return userRepository.save(user);
    }
}
