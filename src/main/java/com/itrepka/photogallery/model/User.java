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
@EqualsAndHashCode(of = "userId")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String login;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Gallery> userGalleries = new ArrayList<>();
    private Role role;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
