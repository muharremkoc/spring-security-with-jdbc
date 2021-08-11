package com.springboot.registerandlogin.controller;

import com.springboot.registerandlogin.model.User;
import com.springboot.registerandlogin.repository.UserRepository;
import com.springboot.registerandlogin.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistAndLoginController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/register" ,method= RequestMethod.GET)
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value="/register")
    public String saveRegisterPage(@ModelAttribute("user") User user, BindingResult result, Model model){
        model.addAttribute("user", user);

        if (result.hasErrors()){
            return "register";
        } else {
            userService.saveUser(user);

        }
        return "index";
    }


    @RequestMapping("/")
    public String index(){
        return "index";
    }


    @PostMapping(value = "/login")
    public String login(@ModelAttribute("user") @RequestBody User user,BindingResult result, Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "logout";

        }

      return "login";
    }


    @GetMapping(value="/login")
    public String getLogin(){
        return "login";
    }
}
