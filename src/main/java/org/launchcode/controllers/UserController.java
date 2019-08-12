package org.launchcode.controllers;


import org.launchcode.model.User;
import org.launchcode.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/user")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll() {
        return userService.getAll();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable Long id) { return userService.getUser(id); }


    @PostMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    public User addNew(@RequestBody User user) {
        return userService.addUser(user);
    }

}
