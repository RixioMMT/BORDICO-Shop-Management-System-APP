package org.BORDICO.Model.DTO;

import lombok.Data;
import org.BORDICO.Model.Enum.RolePosition;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String profileImageUrl;
    private Set<RolePosition> rolePositions;
    private Set<Long> reviewsId;
    private Set<Long> cartsId;
}
