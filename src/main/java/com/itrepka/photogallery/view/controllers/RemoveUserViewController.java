package com.itrepka.photogallery.view.controllers;

import com.itrepka.photogallery.service.dto.UserDto;
import com.itrepka.photogallery.service.exception.UserNotFoundException;
import com.itrepka.photogallery.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RemoveUserViewController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/remove-user/{id}")
    public ModelAndView removeUser(@PathVariable Integer id) throws UserNotFoundException {
        UserDto userDto = userService.deleteUserById(id);
        ModelAndView mv = new ModelAndView("redirect:/admin/home");
        return mv;
    }
}
