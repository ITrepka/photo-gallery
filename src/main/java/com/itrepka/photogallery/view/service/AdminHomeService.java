package com.itrepka.photogallery.view.service;

import com.itrepka.photogallery.service.dto.UserDto;
import com.itrepka.photogallery.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminHomeService {
    @Autowired
    private UserService userService;

    public List<UserDto> getClientListToDisplay() {
        return userService.getAllUsers().stream()
                .filter(userDto -> userDto.getRole().equalsIgnoreCase("USER"))
                .collect(Collectors.toList());
    }
}
