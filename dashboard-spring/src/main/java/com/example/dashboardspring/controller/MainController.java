package com.example.dashboardspring.controller;

import com.example.dashboardspring.service.UserService;
import com.example.dashboardspring.db.UserRepository;
import com.example.dashboardspring.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

    @GetMapping("/")
    public String index() {
        return "/connection";
    }

    @GetMapping("/connection")
    public String login(){
        OAuth2User oAuth2User = getPrincipal();
        User user = getPrincipal2();
        if(user != null || oAuth2User != null){
            return "authentifier";
        }
        return "connection";
    }

    @GetMapping("/authentifier")
    public String connect(Model model){
        model.addAttribute("user", getPrincipal2());
        model.addAttribute("oauth2", getPrincipal());
        return "authentifier";
    }

    private OAuth2User getPrincipal(){
        OAuth2User user = null;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof OAuth2User) {
            user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return user;
    }

    private User getPrincipal2(){
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