package com.example.agile.security;

import com.example.agile.Domain.Role;
import com.example.agile.Repository.RoleRepository;
import com.example.agile.Repository.UserRepository;
import com.example.agile.Domain.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean userExists(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber).isPresent();
    }

    public Role getOrCreateDefaultRole() {
        return roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_USER").build()));
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public void saveUser(AppUser user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
        AppUser user = userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with mobile: " + mobileNumber));
        return new CustomUserDetails(user);
    }
}
