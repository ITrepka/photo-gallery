package com.itrepka.photogallery.view.service;

import com.itrepka.photogallery.config.AuthenticationSystem;
import com.itrepka.photogallery.model.Role;
import com.itrepka.photogallery.model.User;
import com.itrepka.photogallery.repository.UserRepository;
import com.itrepka.photogallery.service.dto.GalleryDto;
import com.itrepka.photogallery.service.dto.PhotoDto;
import com.itrepka.photogallery.service.exception.GalleryNotFoundException;
import com.itrepka.photogallery.service.exception.PhotoNotFoundException;
import com.itrepka.photogallery.service.exception.UserNotFoundException;
import com.itrepka.photogallery.service.services.GalleryService;
import com.itrepka.photogallery.service.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserOperationsService {
    @Autowired
    private AuthenticationSystem authenticationSystem;
    @Autowired
    private GalleryService galleryService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhotoService photoService;


    public List<PhotoDto> getPhotos() throws UserNotFoundException, GalleryNotFoundException, PhotoNotFoundException {
        String name = authenticationSystem.getName();
        User user = userRepository.findByLogin(name)
                .orElseThrow(() -> new UserNotFoundException("Not found user with login = " + name));

        Integer galleryId = user.getGallery().getGalleryId();
        GalleryDto galleryDto = galleryService.getGalleryById(galleryId);
        List<Integer> photosIds = galleryDto.getPhotosIds();
        List<PhotoDto> photos = new ArrayList<>();
        if (photosIds != null) {
            for (Integer photoId : photosIds) {
                PhotoDto photoDto = photoService.getPhotoById(photoId);
                photos.add(photoDto);
            }
        }
        return photos;
    }

    public String getGalleryName() throws GalleryNotFoundException, UserNotFoundException {
        String name = authenticationSystem.getName();
        User user = userRepository.findByLogin(name)
                .orElseThrow(() -> new UserNotFoundException("Not found user with login = " + name));

        Integer galleryId = user.getGallery().getGalleryId();

        GalleryDto galleryDto = galleryService.getGalleryById(galleryId);

        return galleryDto.getName();
    }

    public boolean isAdminLoggedIn() throws UserNotFoundException {
        String name = authenticationSystem.getName();
        User user = userRepository.findByLogin(name)
                .orElseThrow(() -> new UserNotFoundException("Not found user with login = " + name));
        return user.getRole().equals(Role.ADMIN);
    }

    public String getUserLogin() {
       return authenticationSystem.getName();
    }
}
