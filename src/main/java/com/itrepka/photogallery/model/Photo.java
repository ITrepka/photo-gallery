package com.itrepka.photogallery.model;

import java.time.OffsetDateTime;

public class Photo {
    private Integer photoId;
    private String url;
    private String name;
    private Gallery gallery;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
