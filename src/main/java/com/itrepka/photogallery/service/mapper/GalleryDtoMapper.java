package com.itrepka.photogallery.service.mapper;

import com.itrepka.photogallery.model.Gallery;
import com.itrepka.photogallery.model.Photo;
import com.itrepka.photogallery.service.dto.CreateGalleryDto;
import com.itrepka.photogallery.service.dto.GalleryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryDtoMapper {
    public GalleryDto toDto(Gallery gallery) {
        List<Integer> photosIds = gallery.getPhotos() == null ? null : gallery.getPhotos().stream().map(Photo::getPhotoId)
                .collect(Collectors.toList());
        Integer userId = gallery.getUser() == null ? null : gallery.getUser().getUserId();

        return GalleryDto.builder()
                .galleryId(gallery.getGalleryId())
                .name(gallery.getName())
                .userId(userId)
                .photosIds(photosIds)
                .createdAt(gallery.getCreatedAt())
                .updatedAt(gallery.getUpdatedAt())
                .build();
    }

    public Gallery toModel(CreateGalleryDto createGalleryDto) {
        return Gallery.builder()
                .galleryId(null)
                .name(createGalleryDto.getName())
                .user(null)
                .photos(null)
                .createdAt(null)
                .updatedAt(null)
                .build();
    }
}
