package io.nichan.hms.service;

import java.util.List;

import io.nichan.hms.dto.UserDto;
import io.nichan.hms.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
