package sap.gb.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sap.gb.springmvc.model.User;

@Controller
public class UserController {


    @GetMapping("newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("user")
    public String addUser(User user) {
        return "user";
    }

    @GetMapping("users")
    public String getUsers(Model model) {
        return "users";
    }
}
