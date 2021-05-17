package com.example.dashboardspring.controller;

import com.example.dashboardspring.objects.UserDTO;
import com.example.dashboardspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/inscription")
    public String register(@ModelAttribute UserDTO userDTO, Model model){
        model.addAttribute("userDTO", userDTO);
        return "inscription";
    }

    @PostMapping(path="/inscription")
    public String addNewUser (@ModelAttribute UserDTO userDTO, BindingResult bindingResult, RedirectAttributes ra) {

        //check if the user exists
        if(userService.userExists(userDTO.getEmail())) {
            bindingResult.addError(new FieldError("userDTO", "email", "Email address already in use"));
        }
        //check if the passwords match
        if(userDTO.getPassword() != null && userDTO.getRpassword() != null) {
            if(!userDTO.getPassword().equals(userDTO.getRpassword())) {
                bindingResult.addError(new FieldError("userDTO", "rpassword", "Passwords do not match"));
            }
        }
        if(bindingResult.hasErrors()) {
            return "inscription";
        }
        ra.addFlashAttribute("message", "Success! your registration is complete");
        userService.register(userDTO);
        return "redirect:/connection";
    }

}
