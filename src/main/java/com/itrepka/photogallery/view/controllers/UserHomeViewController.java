package com.itrepka.photogallery.view.controllers;

import com.itrepka.photogallery.service.dto.PhotoDto;
import com.itrepka.photogallery.service.exception.GalleryNotFoundException;
import com.itrepka.photogallery.service.exception.PhotoNotFoundException;
import com.itrepka.photogallery.service.exception.UserNotFoundException;
import com.itrepka.photogallery.view.service.UserOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserHomeViewController {
    @Autowired
    private UserOperationsService userOperationsService;

    @GetMapping("/home")
    public ModelAndView displayAdminHome() throws UserNotFoundException, GalleryNotFoundException, PhotoNotFoundException {
        List<PhotoDto> photos = userOperationsService.getPhotos();
        String galleryName = userOperationsService.getGalleryName();
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("photos", photos);
        mv.addObject("galleryName", galleryName);
        return mv;
    }
}
