package com.itrepka.photogallery.view.controllers;

import com.itrepka.photogallery.service.dto.UserDto;
import com.itrepka.photogallery.view.service.AdminHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminHomeViewController {
    @Autowired
    private AdminHomeService adminHomeService;

    @GetMapping("admin/home")
    public ModelAndView displayAdminHome() {
        ModelAndView mv = new ModelAndView("admin/home");
        List<UserDto> clients = adminHomeService.getClientListToDisplay();
        mv.addObject("clients", clients);
        return mv;
    }
}
