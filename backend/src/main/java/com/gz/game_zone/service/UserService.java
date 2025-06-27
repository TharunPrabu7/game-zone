package com.gz.game_zone.service;

import com.gz.game_zone.dto.LoginDto;
import com.gz.game_zone.dto.UserDto;
import com.gz.game_zone.response.LoginResponse;

public interface UserService {
    String addUser(UserDto userDto);
    LoginResponse loginUser(LoginDto loginDto);
}

