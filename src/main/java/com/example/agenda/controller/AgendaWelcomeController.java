package com.example.agenda.controller;

import com.example.agenda.domain.User;
import com.example.agenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AgendaWelcomeController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String initForm(){
        return "login";
    }

    @GetMapping("/createAccount")
    public String initFormCreateAccount(Model model){
        model.addAttribute("user", new User());
        return "createAccount";
    }

    @PostMapping("/createAccount")
    public ModelAndView submitFormCreateAccount(@ModelAttribute User user){
        User u = userService.saveUser(user);
        return new ModelAndView("redirect:/");
    }
}
