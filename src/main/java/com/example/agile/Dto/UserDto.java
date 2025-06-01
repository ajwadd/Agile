package com.example.agile.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class UserDto {

    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 3, max = 50, message = "Name length must be between 3 and 50 characters")
    private String name;

    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    // ✅ Initialize with empty HashSet to prevent null
    private Set<String> roles = new HashSet<>();

    @NotEmpty(message = "Password cannot be null or empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        //  Additional safety check
        this.roles = (roles != null) ? roles : new HashSet<>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}