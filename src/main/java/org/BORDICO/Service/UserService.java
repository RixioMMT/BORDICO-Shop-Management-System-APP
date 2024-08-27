package org.BORDICO.Service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Inputs.UserInput;
import org.BORDICO.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public User addUser(UserInput userInput) throws Exception {
        if (userInput.getEmail().isBlank() || userInput.getPhone().isBlank() || userInput.getPassword().isBlank()) {
            throw new Exception("Empty values are not allowed");
        }
        String phoneInput = userInput.getPhone().replaceAll("\\D", "");
        if (phoneInput.isBlank()) {
            throw new Exception("Phone number must contain at least one digit");
        }
        if (userRepository.findByEmail(userInput.getEmail()).isPresent()) {
            throw new Exception("Email is already used: " + userInput.getEmail());
        }
        User user = User.builder()
                .email(userInput.getEmail())
                .phone(phoneInput)
                .password(passwordEncoder.encode(userInput.getPassword()))
                .firstName(userInput.getFirstName())
                .lastName(userInput.getLastName())
                .dateOfBirth(userInput.getDateOfBirth())
                .roles(userInput.getRoles())
                .build();
        return userRepository.save(user);
    }
}
