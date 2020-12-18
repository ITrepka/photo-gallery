package com.itrepka.photogallery.service.mapper;

import com.itrepka.photogallery.model.Photo;
import com.itrepka.photogallery.service.dto.CreatePhotoDto;
import com.itrepka.photogallery.service.dto.PhotoDto;
import org.springframework.stereotype.Service;

@Service
public class PhotoDtoMapper {
    public PhotoDto toDto(Photo photo) {
        Integer galleryId = photo.getGallery() == null ? null : photo.getGallery().getGalleryId();
        return PhotoDto.builder()
                .photoId(photo.getPhotoId())
                .name(photo.getName())
                .path(photo.getUrl())
                .galleryId(galleryId)
                .createdAt(photo.getCreatedAt())
                .updatedAt(photo.getUpdatedAt())
                .build();
    }

    public Photo toModel(CreatePhotoDto createPhotoDto) {
        return Photo.builder()
                .photoId(null)
                .name(createPhotoDto.getName())
                .url(createPhotoDto.getPath())
                .gallery(null)
                .createdAt(null)
                .updatedAt(null)
                .build();
    }
}
