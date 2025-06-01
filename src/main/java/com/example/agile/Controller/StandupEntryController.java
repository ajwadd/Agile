package com.example.agile.Controller;

import com.example.agile.Dto.StandupEntryDto;
import com.example.agile.Service.IStandupEntryService;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/standup")
@RequiredArgsConstructor
public class StandupEntryController {

    private final IStandupEntryService standupService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitEntry(@RequestBody StandupEntryDto dto) {
        standupService.createEntry(dto);
        return ResponseEntity.ok("Standup entry submitted.");
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<StandupEntryDto>> fetchEntries(
            @RequestParam @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits") String mobile) {
        return ResponseEntity.ok(standupService.getEntriesByMobile(mobile));
    }
}
