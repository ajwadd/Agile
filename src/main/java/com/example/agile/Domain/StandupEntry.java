package com.example.agile.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandupEntry extends BaseEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull(message = "User is required")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user; // Assuming a User entity exists

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "You must say what you did yesterday")
    @Column(length = 1000)
    private String yesterday;

    @NotBlank(message = "You must say what you will do today")
    @Column(length = 1000)
    private String today;

    @NotBlank(message = "You must declare blockers or say 'None'")
    @Column(length = 1000)
    private String blockers;

}
