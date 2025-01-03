package com.travelagency.busbooking.controller;

import com.travelagency.busbooking.dto.UserDto;
import com.travelagency.busbooking.entity.User;
import com.travelagency.busbooking.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findUserByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/rregullorja")
    public String rregullorja(){
        return "rregullorja";
    }

    @GetMapping("/destinacione")
    public String destinacione(){
        return "destinacionet";
    }

    @GetMapping("/kontakt")
    public String kontakt(){
        return "kontakt";
    }

    @GetMapping("/rrethNesh")
    public String rrethNesh()
    {
        return "aboutUs";
    }

    @GetMapping("/tripForm")
    public String createTrip(){
        return "tripForm"
;    }



}