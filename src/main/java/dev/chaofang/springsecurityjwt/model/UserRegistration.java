package dev.chaofang.springsecurityjwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistration {
    private String username;
    private String password;
}
