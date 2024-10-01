package org.BORDICO.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.BORDICO.Model.Entity.Cart;
import org.BORDICO.Model.Entity.Notification;
import org.BORDICO.Model.Entity.Review;
import org.BORDICO.Model.Entity.Role;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class UserDTO {
    private Long id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String profileImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<Role> roles;
    private Set<Notification> notifications;
    private Set<Review> reviews;
    private Set<Cart> carts;
}
