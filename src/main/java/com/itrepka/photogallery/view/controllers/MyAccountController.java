package com.itrepka.photogallery.view.controllers;

import com.itrepka.photogallery.service.dto.UpdateUserDto;
import com.itrepka.photogallery.service.dto.UserDto;
import com.itrepka.photogallery.service.exception.UserInvalidDataException;
import com.itrepka.photogallery.service.exception.UserNotFoundException;
import com.itrepka.photogallery.service.services.UserService;
import com.itrepka.photogallery.view.service.AdminOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyAccountController {
    @Autowired
    private AdminOperationsService adminOperationsService;
    @Autowired
    private UserService userService;

    @GetMapping("/admin/my-account")
    public ModelAndView displayMyAccountForm() throws UserNotFoundException {
        ModelAndView mv = new ModelAndView("/admin/my-account");
        UpdateUserDto updateUserDto = new UpdateUserDto();
        mv.addObject("updateMe", updateUserDto);
        UserDto userDto = adminOperationsService.getMeAsUserDto();
        mv.addObject("userDto", userDto);
        return mv;
    }

    @PostMapping("/admin/my-account")
    public ModelAndView updateUserPassword(@ModelAttribute(name = "updateUser") UpdateUserDto updateUserDto) throws UserInvalidDataException, UserNotFoundException {
        Integer id = adminOperationsService.getMeAsUserDto().getId();
        UserDto userDto = userService.updateUser(id, updateUserDto);
        return new ModelAndView("redirect:/admin/home");
    }
}
