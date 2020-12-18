package com.itrepka.photogallery.view.controllers;

import com.itrepka.photogallery.service.exception.GalleryNotFoundException;
import com.itrepka.photogallery.service.exception.PhotoNotFoundException;
import com.itrepka.photogallery.service.exception.UserNotFoundException;
import com.itrepka.photogallery.service.services.PhotoService;
import com.itrepka.photogallery.view.service.AdminOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class DeletePhotoController {
    @Autowired
    private AdminOperationsService adminOperationsService;

    @GetMapping("/admin/delete-photo/{id}")
    public String deletePhoto(@PathVariable Integer id, HttpServletRequest request) throws PhotoNotFoundException, UserNotFoundException, GalleryNotFoundException, IOException {
        adminOperationsService.deletePhoto(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
