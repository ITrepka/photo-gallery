package com.itrepka.photogallery.view.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserHomeViewController {
    @GetMapping("/home")
    public ModelAndView displayAdminHome() {
        return new ModelAndView("home");
    }
}
