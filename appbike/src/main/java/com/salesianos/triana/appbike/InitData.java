package com.salesianos.triana.appbike;

import com.salesianos.triana.appbike.model.*;
import com.salesianos.triana.appbike.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitData {


        private final PasswordEncoder passwordEncoder;


        @PostConstruct
        public void init(){
                System.out.println(passwordEncoder.encode("user1234"));
        }
}
