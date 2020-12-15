package com.itrepka.photogallery.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "galleryId")
public class Gallery {
    private Integer galleryId;
    private String name;
    private List<Photo> photos = new ArrayList<>();
    private User user;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
