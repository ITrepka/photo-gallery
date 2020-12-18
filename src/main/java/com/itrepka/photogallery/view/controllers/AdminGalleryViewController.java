package com.itrepka.photogallery.view.controllers;

import com.itrepka.photogallery.service.dto.CreatePhotoDto;
import com.itrepka.photogallery.service.dto.GalleryDto;
import com.itrepka.photogallery.service.dto.PhotoDto;
import com.itrepka.photogallery.service.dto.UserDto;
import com.itrepka.photogallery.service.exception.GalleryNotFoundException;
import com.itrepka.photogallery.service.exception.PhotoInvalidDataException;
import com.itrepka.photogallery.service.exception.PhotoNotFoundException;
import com.itrepka.photogallery.service.exception.UserNotFoundException;
import com.itrepka.photogallery.service.services.GalleryService;
import com.itrepka.photogallery.service.services.PhotoService;
import com.itrepka.photogallery.service.services.UserService;
import com.itrepka.photogallery.view.service.AdminOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminGalleryViewController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminOperationsService adminOperationsService;
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/uploads";


    @GetMapping("/admin/gallery/{id}")
    public ModelAndView displayUserGallery(@PathVariable Integer id) throws UserNotFoundException, GalleryNotFoundException, PhotoNotFoundException {
        List<PhotoDto> photos = adminOperationsService.getUserPhotos(id);

        ModelAndView mv = new ModelAndView("/admin/gallery");
        mv.addObject("userId" , id);
        mv.addObject("photos", photos);
        System.out.println(photos);
        return mv;
    }

    @PostMapping("/admin/gallery/{userId}")
    public String upload(@PathVariable Integer userId, Model model, @RequestParam("files") MultipartFile[] files) throws UserNotFoundException, PhotoInvalidDataException, GalleryNotFoundException {
        UserDto userDto = userService.getUserById(userId);
        new File(uploadDirectory + "/" + userDto.getLogin()).mkdir();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String imagePath = "/uploads/" + userDto.getLogin() + "/" + fileName;
            Path fileNameAndPath = Paths.get(uploadDirectory + "/" + userDto.getLogin(), fileName);
            try {
                Files.write(fileNameAndPath, file.getBytes());
                adminOperationsService.addPhotoToDb(imagePath, fileName, userId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/admin/gallery/" + userId;
    }
}
