package com.anushka.schoolDemo.security.auth;

import com.anushka.schoolDemo.security.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil= jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );
        String role = authentication.getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        return jwtUtil.generateToken(request.getUsername(), role);
    }
}
