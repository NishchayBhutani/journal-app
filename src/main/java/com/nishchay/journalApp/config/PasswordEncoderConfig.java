package com.nishchay.journalApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(@Value("${bcrypt-salt}") int bCryptStrength) {
        return new BCryptPasswordEncoder(bCryptStrength);
    }
}
