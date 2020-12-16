package com.itrepka.photogallery.repository;

import com.itrepka.photogallery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
