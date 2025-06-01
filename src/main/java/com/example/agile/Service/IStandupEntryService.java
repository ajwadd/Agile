package com.example.agile.Service;

import com.example.agile.Dto.StandupEntryDto;

import java.util.List;

public interface IStandupEntryService {

    void createEntry(StandupEntryDto dto);

    List<StandupEntryDto> getEntriesByMobile(String mobile);
}
