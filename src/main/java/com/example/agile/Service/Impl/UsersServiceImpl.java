package com.example.agile.Service.Impl;

import com.example.agile.Domain.AppUser;
import com.example.agile.Domain.Role;
import com.example.agile.Dto.UserDto;
import com.example.agile.Repository.RoleRepository;
import com.example.agile.Repository.UserRepository;
import com.example.agile.Service.IUserServices;
import com.example.agile.exception.ResourceNotFoundException;
import com.example.agile.exception.UserAlreadyExistsException;
import com.example.agile.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements IUserServices {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDto userDto) {
        userRepository.findByMobileNumber(userDto.getMobileNumber())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException(user.getName());
                });

        AppUser user = userMapper.userDtoToUser(userDto);

        //  Hash the password before saving
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Convertir Set<String> roles -> Set<Role>
        Set<Role> roles = userDto.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public UserDto fetchUserByMobileNumber(String mobileNumber) {
        AppUser user = userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User", "mobileNumber", mobileNumber));
        return userMapper.userToUserDto(user);
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        boolean isUpdated = false;
        Optional<AppUser> userOpt = userRepository.findByMobileNumber(userDto.getMobileNumber());
        if (userOpt.isPresent()) {
            AppUser user = userOpt.get();
            userMapper.userDtoToUser(userDto, user);

            //  Hash password if it's being updated
            if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }

            Set<Role> roles = userDto.getRoles().stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName)))
                    .collect(Collectors.toSet());

            user.setRoles(roles);
            userRepository.save(user);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteUserByMobileNumber(String mobileNumber) {
        AppUser user = userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User", "mobileNumber", mobileNumber));
        userRepository.delete(user);
        return true;
    }
}