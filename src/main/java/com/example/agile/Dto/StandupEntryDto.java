package com.example.agile.Dto;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class StandupEntryDto {
        private UUID id;
        private String userMobileNumber;
        private LocalDate date;
        private String yesterday;
        private String today;
        private String blockers;
    }


