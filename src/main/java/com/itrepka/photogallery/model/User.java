package com.itrepka.photogallery.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "userId")
public class User {
    private Integer userId;
    private String login;
    private String password;
    private List<Gallery> userGalleries;
    private Role role;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
