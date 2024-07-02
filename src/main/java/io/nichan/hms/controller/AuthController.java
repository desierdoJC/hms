package io.nichan.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import io.nichan.hms.dto.UserDto;
import io.nichan.hms.entity.User;
import io.nichan.hms.service.UserService;
import jakarta.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    
    public AuthController(UserService userService){
        this.userService = userService;
    }

    //method to handle /index page request
    @GetMapping({"/", "/index"})
    public String home(){
        return "index";
    }

    //method to handle /register page request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        //Store form data into a model object
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    //method to handle registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null, "Email already registered.");
        }
        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    //method to handle list of users
    @GetMapping("/users")
    public String users(Model model){

        //Save all users details
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
