package org.BORDICO.Model.Inputs;

import lombok.*;
import org.BORDICO.Model.Enum.RolePosition;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserInput {
    private String email;
    private String phone;
    private String password;
    private String firstName;
    private String lastName;
    private Set<RolePosition> rolePositions;
}
