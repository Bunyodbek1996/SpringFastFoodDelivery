package uz.bprodevelopment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.bprodevelopment.base.role.Role;
import uz.bprodevelopment.base.role.RoleRepo;
import uz.bprodevelopment.base.user.User;
import uz.bprodevelopment.base.user.UserRepo;

import java.util.Set;

import static uz.bprodevelopment.base.constant.Constants.*;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            RoleRepo roleRepo,
            UserRepo userRepo,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            Role roleAdmin = checkAndRetrieveRole(roleRepo, ROLE_ADMIN);
            Role roleWaiter = checkAndRetrieveRole(roleRepo, ROLE_WAITER);
            Role roleUser = checkAndRetrieveRole(roleRepo, ROLE_USER);


            User admin = User.builder()
                    .fullName("Bunyod Khursanaliev")
                    .username("admin")
                    .password(passwordEncoder.encode("123"))
                    .roles(Set.of(roleAdmin))
                    .enabled(true)
                    .build();

            User waiter = User.builder()
                    .fullName("Khurshid Soliev")
                    .username("waiter")
                    .password(passwordEncoder.encode("123"))
                    .roles(Set.of(roleWaiter))
                    .enabled(true)
                    .build();


            User user = User.builder()
                    .fullName("Ali Valiyev")
                    .username("user")
                    .password(passwordEncoder.encode("123"))
                    .roles(Set.of(roleUser))
                    .enabled(true)
                    .build();

            createUserIfNotExists(userRepo, admin);
            createUserIfNotExists(userRepo, waiter);
            createUserIfNotExists(userRepo, user);

        };
    }

    private Role checkAndRetrieveRole(RoleRepo roleRepo, String roleName) {
        Role role = roleRepo.findByName(roleName);
        if (role == null) {
            role = Role.builder().name(roleName).build();
            roleRepo.save(role);
        }
        return role;
    }

    private void createUserIfNotExists(UserRepo userRepo, User user) {
        if (userRepo.findByUsername(user.getUsername()).isEmpty()) {
            userRepo.save(user);
        }
    }
}
