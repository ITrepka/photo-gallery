package com.itrepka.photogallery.view.controllers;

import com.itrepka.photogallery.config.AuthenticationSystem;
import com.itrepka.photogallery.model.User;
import com.itrepka.photogallery.repository.UserRepository;
import com.itrepka.photogallery.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainViewController {
    @Autowired
    private AuthenticationSystem authenticationSystem;
    @Autowired
    private UserRepository userRepository;

    @GetMapping({"", "/", "/index", "/index.html"})
    public String displayHomepage() throws UserNotFoundException {
        String login = authenticationSystem.getName();
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("Not found user with login = " + login));

        String role = user.getRole().name();

        String redirect = role.equalsIgnoreCase("ADMIN") ? "redirect:admin/home" : "redirect:home";

        return redirect;
    }
}
