package com.itrepka.photogallery.model;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "galleryId")
@Entity
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer galleryId;
    private String name;
    @OneToMany(mappedBy = "gallery", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Photo> photos = new ArrayList<>();
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User user;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
