package sap.gb.springmvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sap.gb.springmvc.model.User;
import sap.gb.springmvc.persist.UserRepo;

import java.util.List;

@Controller
public class UserController {
    private UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("user")
    public String addUser(User user) {
        userRepo.saveUser(user);
        return "user";
    }

    @GetMapping("users")
    public String getUsers(Model model) {
        List<User> users = userRepo.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
