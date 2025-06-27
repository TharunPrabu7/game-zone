package com.gz.game_zone.service.impl;

import com.gz.game_zone.dto.LoginDto;
import com.gz.game_zone.dto.UserDto;
import com.gz.game_zone.entity.User;
import com.gz.game_zone.repo.UserRepository;
import com.gz.game_zone.response.LoginResponse;
import com.gz.game_zone.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String addUser(UserDto userDto) {
        User user = new User(
                userDto.getUserId(),
                userDto.getUserName(),
                userDto.getEmail(),
                this.passwordEncoder.encode(userDto.getPassword())
        );
        userRepository.save(user);
        return user.getUserName();
    }

    @Override
    public LoginResponse loginUser(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (user == null)
            return new LoginResponse("User not found", false);
        boolean matches = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
        if (!matches)
            return new LoginResponse("Wrong password", false);

        return new LoginResponse("Login success", true);
    }
}
