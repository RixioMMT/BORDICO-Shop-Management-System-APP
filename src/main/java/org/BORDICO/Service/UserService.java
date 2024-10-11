package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Model.DTO.UserDTO;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Util.ModelMapperUtil;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Entity.Role;
import org.BORDICO.Model.Enum.RolePosition;
import org.BORDICO.Model.Inputs.UserInput;
import org.BORDICO.Repository.RoleRepository;
import org.BORDICO.Repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;
    private final NotificationService notificationService;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;
    private final S3Service s3Service;
    public UserDTO createUser(UserInput userInput) throws CustomException {
        User user = new User();
        user = getUserFromInput(userInput, user);
        user = cartService.createCartForUser(user);
        user = notificationService.createUserNotification(user);
        return modelMapper.map(user, UserDTO.class);
    }
    public PageOutput<UserDTO> getAllUsers(PageInput pageInput) {
        Page<User> users = userRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                users.getNumber(),
                users.getTotalPages(),
                users.getTotalElements(),
                users.getContent().stream()
                        .map(user -> modelMapperUtil.map(user, UserDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public UserDTO getUserById(Long id) throws CustomException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User with ID " + id + " not found"));
        return modelMapper.map(user, UserDTO.class);
    }
    public UserDTO updateUser(Long id, UserInput userInput) throws CustomException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User with ID " + id + " not found"));
        getUserFromInput(userInput, user);
        return modelMapper.map(user, UserDTO.class);
    }
    public void deleteUser(Long id) throws CustomException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User with ID " + id + " not found"));
        userRepository.delete(user);
    }
    public String uploadUserImage(Long userId, MultipartFile profileImage) throws IOException, CustomException {
        String originalFilename = profileImage.getOriginalFilename();
        if (originalFilename == null) {
            throw new CustomException("File name cannot be null");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found with id: " + userId));
        String fileKeyDirectory = "user-profile-images/" + userId;
        String profileImageUrl = s3Service.uploadFileToS3(fileKeyDirectory, originalFilename, profileImage.getBytes());
        user.setProfileImageUrl(profileImageUrl);
        userRepository.save(user);
        return profileImageUrl;
    }
    public User getUserFromInput(UserInput userInput, User user) throws CustomException {
        if (userInput.getEmail().isBlank() || userInput.getPhone().isBlank() || userInput.getPassword().isBlank()) {
            throw new CustomException("Empty values are not allowed");
        }
        String phoneInput = userInput.getPhone().replaceAll("\\D", "");
        if (phoneInput.isBlank()) {
            throw new CustomException("Phone number must contain at least one digit");
        }
        if (userRepository.findByEmail(userInput.getEmail()) != null && !user.getEmail().equals(userInput.getEmail())) {
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
        user.setEmail(userInput.getEmail());
        user.setPhone(phoneInput);
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        user.setFirstName(userInput.getFirstName());
        user.setLastName(userInput.getLastName());
        user.setRoles(roles);
        return userRepository.save(user);
    }
}