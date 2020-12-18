package com.itrepka.photogallery.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatePhotoDto {
    private String path;
    private String name;
    private Integer galleryId;
}
