package dev.chaofang.springsecurityjwt;

import dev.chaofang.springsecurityjwt.model.Grade;
import dev.chaofang.springsecurityjwt.model.Student;
import dev.chaofang.springsecurityjwt.repository.GradeRepository;
import dev.chaofang.springsecurityjwt.repository.StudentRepository;
import dev.chaofang.springsecurityjwt.security.model.User;
import dev.chaofang.springsecurityjwt.security.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static dev.chaofang.springsecurityjwt.security.model.Role.ADMIN;
import static dev.chaofang.springsecurityjwt.security.model.Role.USER;

@SpringBootApplication
@Log4j2
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

    @Bean
    public CommandLineRunner insertAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            log.info("");
            if (userRepository.count() == 0) {

                userRepository.save(
                        User.builder()
                                .username("admin")
                                .password(passwordEncoder.encode("admin"))
                                .role(ADMIN)
                                .build()
                );

                userRepository.save(
                        User.builder()
                                .username("user")
                                .password(passwordEncoder.encode("user"))
                                .role(USER)
                                .build()
                );

            }
        };
    }

    @Bean
    public CommandLineRunner populateStudents(StudentRepository repository, GradeRepository gradeRepository) {
        return args -> {
            if (repository.count() == 0) {
                var student1 = repository.save(
                        Student.builder()
                                .name("Mario")
                                .surname("Rossi")
                                .birthDate(LocalDate.of(1991, 1, 12))
                                .classRoom(3)
                                .build()
                );

                var student2 = repository.save(
                        Student.builder()
                                .name("Maria")
                                .surname("Bianchi")
                                .birthDate(LocalDate.of(1992, 3, 14))
                                .classRoom(5)
                                .build()
                );

                gradeRepository.save(
                        Grade.builder()
                                .student(student1)
                                .subject("Italiano")
                                .score(10)
                                .build()
                );

            }

        };
    }

}
