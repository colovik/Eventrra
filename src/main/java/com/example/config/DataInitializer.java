package com.example.config;

import com.example.model.Admin;
import com.example.model.Enumerations.Role;
import com.example.model.Location;
import com.example.repository.LocationRepository;
import com.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Configuration
@Component
public class DataInitializer {

    private final LocationRepository locationsRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public DataInitializer(LocationRepository locationsRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.locationsRepository = locationsRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner loadData() {
        return args -> {

            if (((userRepository.findByRole(Role.ROLE_ADMIN)) == null)) {
                userRepository.save(new Admin(LocalDate.now(), "admin", "admin", passwordEncoder.encode("admin"), "000000", Role.ROLE_ADMIN, 0));
            }

            if (locationsRepository.count() == 0) {
                locationsRepository.save(new Location("Location 1", "071001001", 1000));
                locationsRepository.save(new Location("Location 2", "072987123", 2000));
                locationsRepository.save(new Location("Location 3", "076124231", 3000));
            }
        };
    }
}
