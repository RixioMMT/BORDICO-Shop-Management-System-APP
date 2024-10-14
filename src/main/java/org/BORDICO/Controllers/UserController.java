package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.UserDTO;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.UserInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserInput userInput) throws CustomException {
        UserDTO newUser = userService.createUser(userInput);
        return ResponseEntity.ok(newUser);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<PageOutput<UserDTO>> getAllUsers(PageInput pageInput) {
        PageOutput<UserDTO> usersPage = userService.getAllUsers(pageInput);
        return ResponseEntity.ok(usersPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) throws CustomException {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserInput userInput) throws CustomException {
        UserDTO updatedUser = userService.updateUser(id, userInput);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws CustomException {
        userService.deleteUser(id);
        return ResponseEntity.ok("User with ID " + id + " was deleted successfully");
    }
    @PutMapping("/{userId}/profile-image")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<String> uploadUserImage(@PathVariable Long userId, @RequestParam("file") MultipartFile profileImage) throws IOException, CustomException {
        String imageUrl = userService.uploadUserImage(userId, profileImage);
        return ResponseEntity.ok(imageUrl);
    }
}