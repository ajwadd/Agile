package com.example.agile.Repository;

import com.example.agile.Domain.StandupEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StandupEntryRepository extends JpaRepository<StandupEntry, UUID> {
        List<StandupEntry> findByUser_MobileNumber(String mobileNumber);
    }


