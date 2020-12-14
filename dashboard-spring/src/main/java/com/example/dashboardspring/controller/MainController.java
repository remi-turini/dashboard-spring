package com.example.dashboardspring.controller;

import com.example.dashboardspring.service.UserService;
import com.example.dashboardspring.db.UserRepository;
import com.example.dashboardspring.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@CrossOrigin("*")
@Controller
@RequestMapping() // This means URL's start with /demo (after Application path)
public class MainController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/sign-in")
    public String login(){
        User user = getPrincipal();
        if(user != null) {
            return "authenticated";
        }
        return "sign-in";
    }

    @GetMapping("/authenticated")
    public String connect(Model model){
        model.addAttribute("user", getPrincipal());
        return "authenticated";
    }

    private User getPrincipal(){
        User user = null;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return user;
    }

    /*
    @GetMapping()
    public @ResponseBody Iterable<Users> getAllUsers() {
        // This returns a JSON or XML with the users
        return usersRepository.findAll();
    }
    */

}