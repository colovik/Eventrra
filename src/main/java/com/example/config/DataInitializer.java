package com.example.config;

import com.example.model.Admin;
import com.example.model.Allergens;
import com.example.model.Enumerations.Role;
import com.example.repository.AllergenRepository;
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
    private final UserRepository userRepository;
    private final AllergenRepository allergenRepository;
    private final PasswordEncoder passwordEncoder;


    public DataInitializer(UserRepository userRepository, AllergenRepository allergenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.allergenRepository = allergenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner loadData() {
        return args -> {

            if (((userRepository.findByRole(Role.ROLE_ADMIN)) == null)) {
                userRepository.save(new Admin(LocalDate.now(), "admin", "admin", passwordEncoder.encode("admin"), "000000", Role.ROLE_ADMIN, 0));
            }

            if (allergenRepository.count() == 0) {
                allergenRepository.save(new Allergens("Peanuts"));
                allergenRepository.save(new Allergens("Tree Nuts"));
                allergenRepository.save(new Allergens("Milk"));
                allergenRepository.save(new Allergens("Eggs"));
                allergenRepository.save(new Allergens("Wheat"));
                allergenRepository.save(new Allergens("Soy"));
                allergenRepository.save(new Allergens("Fish"));
                allergenRepository.save(new Allergens("Shellfish"));
                allergenRepository.save(new Allergens("Sesame"));
                allergenRepository.save(new Allergens("Other"));
            }
        };
    }
}
