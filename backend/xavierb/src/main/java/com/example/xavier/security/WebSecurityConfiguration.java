package com.example.xavier.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChanin(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
            auth->auth.requestMatchers(new AntPathRequestMatcher("/users/*", "POST")).permitAll()
            .anyRequest().authenticated()
            );
        http.sessionManagement(
            session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
        http.csrf(csrf->csrf.disable());
        http.httpBasic(Customizer.withDefaults());
        http.headers(
            header->header.frameOptions(frame->frame.sameOrigin())
            );
        http.oauth2ResourceServer(
            oauth2->oauth2.jwt(Customizer.withDefaults())
            );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Generating KeyPairs
    @Bean
    public KeyPair keyPair(){
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator=KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.genKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //Creating public and private keys
    @Bean
    public RSAKey rsaKey(KeyPair keypair){
        return new RSAKey.Builder((RSAPublicKey)keypair.getPublic())
        .privateKey(keypair.getPrivate())
        .keyID(UUID.randomUUID().toString())
        .build();
    }

    @Bean
    public JWKSource<SecurityContext> jskSource(RSAKey rsaKey){
        var jwkSet=new JWKSet(rsaKey);
        return (jwkSelector,context)->jwkSelector.select(jwkSet);
    }
    // Encoder and Decoder

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource){
        return new NimbusJwtEncoder(jwkSource);
    }
    @Bean
    public JwtDecoder JwtDecoder(RSAKey rsaKey) throws JOSEException{
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
    }
}
