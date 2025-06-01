package com.example.agile.Controller;

import com.example.agile.Domain.AppUser;
import com.example.agile.Domain.Role;
import com.example.agile.Dto.LoginRequest;
import com.example.agile.Dto.LoginResponse;
import com.example.agile.Dto.RegisterRequest;
import com.example.agile.Dto.ResponseDto;
import com.example.agile.security.CustomUserDetails;
import com.example.agile.security.CustomUserDetailsService;
import com.example.agile.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getMobileNumber(), request.getPassword())
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401)
                    .body(new ResponseDto("401", "Invalid mobile number or password"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        // Vérifier si le numéro existe déjà
        if (userDetailsService.userExists(request.getMobileNumber())) {
            return ResponseEntity.status(409)
                    .body(new ResponseDto("409", "Mobile number already registered"));
        }

        // Récupérer ou créer le rôle par défaut
        Role role = userDetailsService.getOrCreateDefaultRole(); // Exemple: "ROLE_USER"

        // Créer l'utilisateur
        AppUser user = AppUser.builder()
                .name(request.getName())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .password(userDetailsService.encodePassword(request.getPassword()))
                .roles(Set.of(role))
                .build();

        userDetailsService.saveUser(user);

        return ResponseEntity.status(201)
                .body(new ResponseDto("201", "User registered successfully"));
    }

}

