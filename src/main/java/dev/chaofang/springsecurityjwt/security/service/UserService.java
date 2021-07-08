package dev.chaofang.springsecurityjwt.security.service;

import dev.chaofang.springsecurityjwt.exception.UserExistingException;
import dev.chaofang.springsecurityjwt.model.UserRegistration;
import dev.chaofang.springsecurityjwt.security.model.Role;
import dev.chaofang.springsecurityjwt.security.model.User;
import dev.chaofang.springsecurityjwt.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component("userDetailsService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format("User with username %s does not exist", username)
                        )
                );
    }

    public User registerUser(UserRegistration userRegistration) {
        User newUser = User.builder()
                .username(userRegistration.getUsername())
                .password(encoder.encode(userRegistration.getPassword()))
                .role(Role.USER)
                .build();
        try {
            return userRepository.save(newUser);
        } catch (DataIntegrityViolationException exception) {
            throw new UserExistingException();
        }
    }
}
