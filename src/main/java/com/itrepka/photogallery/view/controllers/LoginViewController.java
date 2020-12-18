package com.itrepka.photogallery.view.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginViewController {
    @GetMapping({"/login"})
    public ModelAndView displayHomepage() {
        return new ModelAndView("login");
    }

}
