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
        Integer galleryId = user.getGallery() == null ? null : user.getGallery().getGalleryId();
        return UserDto.builder()
                .id(user.getUserId())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .login(user.getLogin())
                .role(user.getRole().name())
                .galleryId(galleryId)
                .build();
    }

    public User toModel(CreateUserDto createUserDto) {
        return User.builder()
                .userId(null)
                .login(createUserDto.getLogin())
                .password(null)
                .role(null)
                .gallery(null)
                .build();
    }
}
