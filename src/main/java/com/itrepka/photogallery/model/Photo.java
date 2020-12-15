package com.itrepka.photogallery.model;

import lombok.*;

import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "photoId")
public class Photo {
    private Integer photoId;
    private String url;
    private String name;
    private Gallery gallery;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
