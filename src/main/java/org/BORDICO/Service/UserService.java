package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Model.DTO.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.Entity.Cart;
import org.BORDICO.Model.Entity.Notification;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Entity.Role;
import org.BORDICO.Model.Enum.RolePosition;
import org.BORDICO.Model.Inputs.UserInput;
import org.BORDICO.Repository.CartRepository;
import org.BORDICO.Repository.NotificationRepository;
import org.BORDICO.Repository.RoleRepository;
import org.BORDICO.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;
    private final LambdaService lambdaService;
    private final S3Client s3Client;
    @Value("${aws.s3.bucket-name}")
    private String bucketName;
    public UserDTO addUser(UserInput userInput) throws CustomException {
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
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }
    public String uploadUserImage(Long userId, MultipartFile profileImage) throws IOException, CustomException {
        String originalFilename = profileImage.getOriginalFilename();
        if (originalFilename == null) {
            throw new CustomException("File name cannot be null");
        }
        User user = userRepository.findById(userId).orElseThrow(() ->
                new CustomException("User not found with id: " + userId));
        String fileKey = "user-profile-images/" + userId + "/" + URLEncoder.encode(originalFilename, StandardCharsets.UTF_8);
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileKey)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(profileImage.getBytes()));
        String profileImageUrl = "https://" + bucketName + ".s3.amazonaws.com/" + fileKey;
        user.setProfileImageUrl(profileImageUrl);
        userRepository.save(user);
        return profileImageUrl;
    }
    public User addUserWithNotification(UserInput userInput) throws CustomException {
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
        userRepository.save(user);
        Set<Notification> notifications = new HashSet<>();
        Notification notification = Notification.builder()
                .notificationName("User Created")
                .notificationDescription("User " + user.getUsername() + " has been created.")
                .user(user)
                .build();
        notificationRepository.save(notification);
        notifications.add(notification);
        user.setNotifications(notifications);
        lambdaService.invokeUserCreatedNotification(notification);
        userRepository.save(user);
        return user;
    }
}
