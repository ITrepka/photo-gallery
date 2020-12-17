package com.itrepka.photogallery.view.controllers;

import com.itrepka.photogallery.service.dto.CreateUserDto;
import com.itrepka.photogallery.service.dto.UserDto;
import com.itrepka.photogallery.service.exception.GalleryInvalidDataException;
import com.itrepka.photogallery.service.exception.UserAlreadyExistsException;
import com.itrepka.photogallery.service.exception.UserInvalidDataException;
import com.itrepka.photogallery.service.exception.UserNotFoundException;
import com.itrepka.photogallery.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddUserViewController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/add-user")
    public ModelAndView displayCreatingUserForm() {
        ModelAndView mv = new ModelAndView("/admin/add-user");
        CreateUserDto user = new CreateUserDto();
        mv.addObject("user", user);
        return mv;
    }

    @PostMapping("/admin/add-user")
    public ModelAndView addNewUser(@ModelAttribute(name = "user") CreateUserDto createUserDto) throws UserNotFoundException, UserAlreadyExistsException, UserInvalidDataException, GalleryInvalidDataException {
        UserDto userDto = userService.addNewUser(createUserDto);
        ModelAndView mv = new ModelAndView("redirect:/admin/home");
        return mv;
    }
}
