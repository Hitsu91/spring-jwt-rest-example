package dev.chaofang.springsecurityjwt.controller;

import dev.chaofang.springsecurityjwt.model.UserRegistration;
import dev.chaofang.springsecurityjwt.security.model.User;
import dev.chaofang.springsecurityjwt.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("who-am-i")
    public User whoAmI(@AuthenticationPrincipal String username) {
        return userService.loadUserByUsername(username);
    }

    @PostMapping("sign-up")
    public User register(@RequestBody UserRegistration userRegistration) {
        return userService.registerUser(userRegistration);
    }
}
