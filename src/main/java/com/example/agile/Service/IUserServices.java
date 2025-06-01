package com.example.agile.Service;

import com.example.agile.Dto.UserDto;

public interface IUserServices {

        void createUser(UserDto userDto);

        UserDto fetchUserByMobileNumber(String mobileNumber);

        boolean updateUser(UserDto userDto);

        boolean deleteUserByMobileNumber(String mobileNumber);
    }


