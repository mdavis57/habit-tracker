package org.launchcode.controllers;

import org.launchcode.model.Habit;
import org.launchcode.service.HabitService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class HabitController {
    private HabitService habitService;

    public HabitController(HabitService habitService) {this.habitService = habitService; }


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
    public Habit addHabit(@RequestBody Habit habit) {
        return habitService.addHabit(habit);
    }


}
