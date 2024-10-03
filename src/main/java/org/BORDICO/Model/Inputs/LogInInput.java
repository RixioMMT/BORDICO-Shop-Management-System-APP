package org.BORDICO.Model.Inputs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LogInInput {
    private String email;
    private String password;
}