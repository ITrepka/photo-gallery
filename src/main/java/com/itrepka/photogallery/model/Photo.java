package com.itrepka.photogallery.model;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "photoId")
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer photoId;
    private String url;
    private String name;
    @ManyToOne
    private Gallery gallery;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
