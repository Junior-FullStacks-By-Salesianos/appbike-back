package com.salesianos.triana.appbike;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData {

        private final PasswordEncoder passwordEncoder;

        @PostConstruct
        public void init() {
                System.out.println(passwordEncoder.encode("user1234"));
        }
}
