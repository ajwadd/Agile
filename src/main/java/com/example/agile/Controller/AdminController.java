package com.example.agile.Controller;

import com.example.agile.Dto.ResponseDto;
import com.example.agile.Dto.UserDto;
import com.example.agile.Service.IUserServices;
import com.example.agile.constants.UsersConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@Validated
public class AdminController {

    private final IUserServices iUserService;

    public AdminController(IUserServices iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/create-user")
    @PreAuthorize("hasAuthority('ROLE_Admin')")
    public ResponseEntity<ResponseDto> createUserByAdmin(@Valid @RequestBody UserDto userDto) {
        iUserService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(UsersConstants.STATUS_201, UsersConstants.MESSAGE_201));
    }

    @GetMapping("/fetch-user")
    public ResponseEntity<UserDto> fetchUserDetails(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber) {
        UserDto userDto = iUserService.fetchUserByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PutMapping("/update-user")
    public ResponseEntity<ResponseDto> updateUserDetails(@Valid @RequestBody UserDto userDto) {
        boolean isUpdated = iUserService.updateUser(userDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UsersConstants.STATUS_200, UsersConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(UsersConstants.STATUS_417, UsersConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<ResponseDto> deleteAccountDetails(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber) {
        boolean isDeleted = iUserService.deleteUserByMobileNumber(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UsersConstants.STATUS_200, UsersConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(UsersConstants.STATUS_417, UsersConstants.MESSAGE_417_DELETE));
        }
    }
}
