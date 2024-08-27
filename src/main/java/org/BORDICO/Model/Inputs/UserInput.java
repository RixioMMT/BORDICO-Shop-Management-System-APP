package org.BORDICO.Model.Inputs;

import lombok.*;
import org.BORDICO.Model.Entity.Role;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class UserInput {
    private String email;
    private String phone;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
    private Set<Role> roles;
}
