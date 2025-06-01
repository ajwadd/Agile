package com.example.agile.Service.Impl;

import com.example.agile.Domain.AppUser;
import com.example.agile.Domain.StandupEntry;
import com.example.agile.Dto.StandupEntryDto;
import com.example.agile.Repository.StandupEntryRepository;
import com.example.agile.Repository.UserRepository;
import com.example.agile.Service.IStandupEntryService;
import com.example.agile.exception.ResourceNotFoundException;
import com.example.agile.mapper.StandupEntryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StandupEntryServiceImpl implements IStandupEntryService {

    private final StandupEntryRepository entryRepository;
    private final UserRepository userRepository;
    private final StandupEntryMapper entryMapper;

    @Override
    public void createEntry(StandupEntryDto dto) {
        AppUser user = userRepository.findByMobileNumber(dto.getUserMobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException("User", "mobile", dto.getUserMobileNumber()));

        StandupEntry entry = entryMapper.toEntityWithUser(dto, user);
        entryRepository.save(entry);
    }


    @Override
    public List<StandupEntryDto> getEntriesByMobile(String mobile) {
        return entryRepository.findByUser_MobileNumber(mobile).stream()
                .map(entryMapper::toDto)
                .collect(Collectors.toList());
    }
}
