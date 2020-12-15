package com.itrepka.photogallery.model;

import java.time.OffsetDateTime;
import java.util.List;

public class User {
    private Integer userId;
    private String login;
    private String password;
    private List<Gallery> userGalleries;
    private Role role;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
