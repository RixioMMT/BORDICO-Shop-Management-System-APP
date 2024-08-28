package org.BORDICO.Model.Inputs;

import lombok.*;
import org.BORDICO.Model.Enum.RolePosition;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class RoleInput {
    private RolePosition rolePosition;
}
