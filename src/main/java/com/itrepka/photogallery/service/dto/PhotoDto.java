package com.itrepka.photogallery.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PhotoDto {
    private Integer photoId;
    private String url;
    private String name;
    private Integer galleryId;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
