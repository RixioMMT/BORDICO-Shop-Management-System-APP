package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Inputs.UserInput;
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
    public ResponseEntity<User> addUser(@RequestBody UserInput userInput) throws CustomException {
        User newUser = userService.addUser(userInput);
        return ResponseEntity.ok(newUser);
    }
    @PutMapping("/{userId}/profile-image")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> uploadUserImage(@PathVariable Long userId, @RequestParam("file") MultipartFile profileImage) throws IOException, CustomException {
        String imageUrl = userService.uploadUserImage(userId, profileImage);
        return ResponseEntity.ok(imageUrl);
    }
    @PostMapping("/add-user-notification")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> addUserWithNotification(@RequestBody UserInput userInput) throws CustomException {
        User newUser = userService.addUserWithNotification(userInput);
        return ResponseEntity.ok(newUser);
    }
}