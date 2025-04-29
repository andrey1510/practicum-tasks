package ru.practicum.patterns.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.patterns.domain.LoginRequest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtEncoder jwtEncoder;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return generateToken(request.username(), request.password());
    }

    private String generateToken(String username, String password) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(1, ChronoUnit.HOURS))
            .subject(username)
            .claim("roles", getRoles(username, password))
            .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private List<String> getRoles(String username, String password) {
        if ("farmer".equals(username)) return List.of("FARMER");
        if ("seller".equals(username)) return List.of("SELLER");
        return List.of();
    }
}
