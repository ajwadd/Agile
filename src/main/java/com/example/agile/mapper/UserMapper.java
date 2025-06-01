package com.example.agile.mapper;

import com.example.agile.Domain.AppUser;
import com.example.agile.Domain.Role;
import com.example.agile.Dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(AppUser user);

    AppUser userDtoToUser(UserDto userDto);

    void userDtoToUser(UserDto userDto, @MappingTarget AppUser user);

    @Mapping(target = "roles", expression = "java(mapRolesToStrings(user.getRoles()))")
    UserDto toDto(AppUser user);
    default Set<String> mapRolesToStrings(Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }



    @Mapping(target = "roles", expression = "java(mapStringsToRoles(dto.getRoles()))")
    AppUser toEntity(UserDto dto);
    default Set<Role> mapStringsToRoles(Set<String> roleNames) {
        if (roleNames == null) return null;
        return roleNames.stream()
                .map(name -> Role.builder().name(name).build()) // adjust if Role is not using Lombok
                .collect(Collectors.toSet());
    }

}
