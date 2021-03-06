package com.itrepka.photogallery.model;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

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
    @Column(unique = true)
    private String login;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Gallery gallery;
    private Role role;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
