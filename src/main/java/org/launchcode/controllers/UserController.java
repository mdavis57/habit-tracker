package org.launchcode.controllers;


import org.launchcode.model.ApplicationUser;
import org.launchcode.model.Habit;
import org.launchcode.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ApplicationUser addUser(@RequestBody ApplicationUser applicationUser) {
        return userService.addUser(applicationUser);
    }

    @GetMapping("/user/id")
    public ApplicationUser getUserIdForRequest(HttpServletRequest request)
    {
        return userService.getUser(request);
    }




}
