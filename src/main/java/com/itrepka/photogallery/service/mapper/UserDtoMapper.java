package com.itrepka.photogallery.service.mapper;

import com.itrepka.photogallery.model.Gallery;
import com.itrepka.photogallery.model.User;
import com.itrepka.photogallery.service.dto.CreateUserDto;
import com.itrepka.photogallery.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDtoMapper {

    public UserDto toDto(User user) {
        List<Integer> userGalleriesIds = user.getUserGalleries() == null ? null : user.getUserGalleries().stream()
                .map(Gallery::getGalleryId)
                .collect(Collectors.toList());
        return UserDto.builder()
                .id(user.getUserId())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .login(user.getLogin())
                .role(user.getRole().name())
                .galleriesIds(userGalleriesIds)
                .build();

    }

    public User toModel(CreateUserDto createUserDto) {
        return User.builder()
                .userId(null)
                .login(createUserDto.getLogin())
                .password(null)
                .role(null)
                .userGalleries(null)
                .build();
    }
}
