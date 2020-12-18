package com.itrepka.photogallery.view.service;

import com.itrepka.photogallery.config.AuthenticationSystem;
import com.itrepka.photogallery.model.Photo;
import com.itrepka.photogallery.service.dto.*;
import com.itrepka.photogallery.service.exception.GalleryNotFoundException;
import com.itrepka.photogallery.service.exception.PhotoInvalidDataException;
import com.itrepka.photogallery.service.exception.PhotoNotFoundException;
import com.itrepka.photogallery.service.exception.UserNotFoundException;
import com.itrepka.photogallery.service.services.GalleryService;
import com.itrepka.photogallery.service.services.PhotoService;
import com.itrepka.photogallery.service.services.UserService;
import com.itrepka.photogallery.view.controllers.AdminGalleryViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminOperationsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private GalleryService galleryService;
    @Autowired
    private AuthenticationSystem authenticationSystem;

    public List<UserDto> getClientListToDisplay() {
        return userService.getAllUsers().stream()
                .filter(userDto -> userDto.getRole().equalsIgnoreCase("USER"))
                .collect(Collectors.toList());
    }

    public void addPhotoToDb(String imagePath, String fileName, Integer userId) throws UserNotFoundException, PhotoInvalidDataException, GalleryNotFoundException {
        UserDto user = userService.getUserById(userId);
        Integer galleryId = user.getGalleryId();
        CreatePhotoDto createPhotoDto = CreatePhotoDto.builder()
                .path(imagePath)
                .name(fileName)
                .galleryId(galleryId)
                .build();
        photoService.addNewPhoto(createPhotoDto);
    }

    public List<PhotoDto> getUserPhotos(Integer id) throws UserNotFoundException, GalleryNotFoundException, PhotoNotFoundException {
        UserDto user = userService.getUserById(id);
        System.out.println("user" + user);
        Integer galleryId = user.getGalleryId();
        GalleryDto gallery = galleryService.getGalleryById(galleryId);
        System.out.println(gallery);
        List<Integer> photosIds = gallery.getPhotosIds();
        System.out.println(photosIds);
        List<PhotoDto> photos = new ArrayList<>();

        for (Integer photoId : photosIds) {
            PhotoDto photo = photoService.getPhotoById(photoId);
            photos.add(photo);
        }

        return photos;
    }

    public void deletePhoto(Integer id) throws PhotoNotFoundException, GalleryNotFoundException, UserNotFoundException, IOException {
        PhotoDto photo = photoService.getPhotoById(id);
        Integer galleryId = photo.getGalleryId();
        GalleryDto gallery = galleryService.getGalleryById(galleryId);
        Integer userId = gallery.getUserId();
        UserDto user = userService.getUserById(userId);

        Path path = Paths.get(AdminGalleryViewController.uploadDirectory + "/" + user.getLogin() + "/" + photo.getName());
        Files.delete(path);
        photoService.deletePhotoById(id);
    }

    public UserDto getMeAsUserDto() throws UserNotFoundException {
        String login = authenticationSystem.getName();
        return userService.getUserByLogin(login);
    }
}
