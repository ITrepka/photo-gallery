package com.itrepka.photogallery.view.controllers;

import com.itrepka.photogallery.config.AuthenticationSystem;
import com.itrepka.photogallery.model.Role;
import com.itrepka.photogallery.model.User;
import com.itrepka.photogallery.repository.UserRepository;
import com.itrepka.photogallery.service.exception.UserNotFoundException;
import com.itrepka.photogallery.view.service.AdminOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainViewController {
    @Autowired
    private AuthenticationSystem authenticationSystem;
    @Autowired
    private AdminOperationsService adminOperationsService;

    @GetMapping({"", "/", "/index", "/index.html"})
    public String displayHomepage() throws UserNotFoundException {
        String role = adminOperationsService.getRole();

        String redirect = role.equalsIgnoreCase("ADMIN") ? "redirect:admin/home" : "redirect:home";

        return redirect;
    }
}
