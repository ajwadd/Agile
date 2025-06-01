package com.example.agile.mapper;

import com.example.agile.Domain.AppUser;
import com.example.agile.Domain.StandupEntry;
import com.example.agile.Dto.StandupEntryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StandupEntryMapper {

    @Mapping(source = "user.mobileNumber", target = "userMobileNumber")
    StandupEntryDto toDto(StandupEntry entry);

    @Mapping(target = "user", ignore = true) // ne pas mapper automatiquement
    StandupEntry toEntity(StandupEntryDto dto);

    void updateFromDto(StandupEntryDto dto, @MappingTarget StandupEntry entry);

    // Méthode personnalisée que tu appelleras manuellement
    default StandupEntry toEntityWithUser(StandupEntryDto dto, AppUser user) {
        StandupEntry entry = toEntity(dto);
        entry.setUser(user);
        return entry;
    }
}