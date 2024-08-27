package org.BORDICO.Resolver;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Inputs.UserInput;
import org.BORDICO.Service.UserService;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserResolver {
    private final UserService userService;
    public User addUser(UserInput userInput) throws Exception {
        return userService.addUser(userInput);
    }
}
