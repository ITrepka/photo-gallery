package com.itrepka.photogallery.service.dto;

import com.itrepka.photogallery.model.Photo;
import com.itrepka.photogallery.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GalleryDto {
    private Integer galleryId;
    private String name;
    private List<Integer> photosIds;
    private Integer userId;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
