package com.example.agile.Repository;

import com.example.agile.Domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByMobileNumber(String mobileNumber);

}
