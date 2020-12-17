package com.itrepka.photogallery.view.controllers;

import com.itrepka.photogallery.service.dto.UpdateUserDto;
import com.itrepka.photogallery.service.dto.UserDto;
import com.itrepka.photogallery.service.exception.UserInvalidDataException;
import com.itrepka.photogallery.service.exception.UserNotFoundException;
import com.itrepka.photogallery.service.services.UserService;
import com.itrepka.photogallery.view.service.AdminOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EditUserViewController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/edit-user/{id}")
    public ModelAndView displayEditForm(@PathVariable Integer id) throws UserNotFoundException {
        ModelAndView mv = new ModelAndView("/admin/edit-user");
        UpdateUserDto updateUserDto = new UpdateUserDto();
        UserDto userDto = userService.getUserById(id);
        mv.addObject("updateUser", updateUserDto);
        mv.addObject("userDto", userDto);
        return mv;
    }

    @PostMapping("/admin/edit-user/{id}")
    public ModelAndView updateUserPassword(@PathVariable Integer id, @RequestBody UpdateUserDto updateUserDto) throws UserInvalidDataException, UserNotFoundException {
        UserDto userDto = userService.updateUser(id, updateUserDto);
        return new ModelAndView("/admin/home?edited="+userDto.getLogin());
    }
}
