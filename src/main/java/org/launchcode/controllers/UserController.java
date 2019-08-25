package org.launchcode.controllers;


import org.launchcode.model.CustomUserDetails;
import org.launchcode.model.User;
import org.launchcode.service.CustomUserDetailsService;
import org.launchcode.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RequestMapping("/")
@RestController
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/public")
    public String index(){
        return "public message";
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/secured")
    public String alternate() {
        return "secured message";
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll() {
        return userService.getAll();
    }


    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable Long id) { return userService.getUser(id); }


    @PostMapping("/user/new")
    @ResponseStatus(HttpStatus.OK)
    public User addNew(@Valid @RequestBody User user) {

        return userService.addUser(user);

    }


}
