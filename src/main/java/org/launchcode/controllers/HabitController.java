package org.launchcode.controllers;

import org.launchcode.model.ApplicationUser;
import org.launchcode.model.Habit;
import org.launchcode.service.HabitService;
import org.launchcode.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class HabitController {

    private HabitService habitService;
    private UserService userService;

    public HabitController(HabitService habitService, UserService userService) {
        this.habitService = habitService;
        this.userService = userService;
    }


    @GetMapping("/user/id/habits")
    public List<Habit> getHabitsForUser(HttpServletRequest request)
    {
        return habitService.getUserHabits(request);
    }

    @GetMapping("/habits")
    public List<Habit> getHabits() {
        return habitService.getAll();
    }

    @PostMapping("/newhabit")
    public Habit addHabit(@RequestBody Habit habit, HttpServletRequest request) {
        ApplicationUser appUser = userService.getUser(request);
        habit.setApplicationUser(appUser);
        return habitService.addHabit(habit);
    }
}
