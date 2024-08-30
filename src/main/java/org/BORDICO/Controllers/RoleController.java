package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.Entity.Role;
import org.BORDICO.Model.Inputs.RoleInput;
import org.BORDICO.Service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    @PostMapping
    public ResponseEntity<Role> addRole(@RequestBody RoleInput roleInput) throws CustomException {
        Role newRole = roleService.addRole(roleInput);
        return ResponseEntity.ok(newRole);
    }
}