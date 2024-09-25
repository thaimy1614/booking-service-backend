package com.s_service.s_service.config;

import com.s_service.s_service.model.Account;
import com.s_service.s_service.model.Role;
import com.s_service.s_service.repository.AccountRepository;
import com.s_service.s_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class ApplicationConfiguration {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Bean
    ApplicationRunner applicationRunner(AccountRepository accountRepository) {
        return args -> {
            // Default student role
            if (!roleRepository.existsById(1)) {
                roleRepository.save(
                        Role.builder()
                                .id(1)
                                .role(Role.UserRole.ADMIN)
                                .description("ADMIN ROLE")
                                .build()
                );
                roleRepository.save(
                        Role.builder()
                                .id(2)
                                .role(Role.UserRole.STAFF)
                                .description("STAFF ROLE")
                                .build()
                );
                roleRepository.save(
                        Role.builder()
                                .id(3)
                                .role(Role.UserRole.CUSTOMER)
                                .description("CUSTOMER ROLE")
                                .build()
                );
            }

            // Default admin account
            if (accountRepository.findByUsername("admin").isEmpty()) {
                //admin account
                Account account = Account
                        .builder()
                        .username("admin")
                        .password(passwordEncoder.encode("123"))
                        .roles(roleRepository.findById(1).orElse(Role.builder().role(Role.UserRole.ADMIN).description("ADMIN ROLE").build()))
                        .status(Account.AccountStatus.ACTIVE)
                        .build();
                accountRepository.save(account);
            }
        };
    }
}
