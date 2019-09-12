package org.launchcode.service;


import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.launchcode.model.ApplicationUser;
import org.launchcode.model.Habit;
import org.launchcode.repository.HabitRepository;
import org.launchcode.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.launchcode.security.SecurityConstants.*;

@Service
@Slf4j
public class HabitService {
    private UserRepository userRepository;
    private HabitRepository habitRepository;



    public HabitService(HabitRepository habitRepository, UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;

    }



    public Habit addHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public List<Habit> getUserHabits(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token == null)
        {
            throw new RuntimeException("token is null");
        }
        SecretKey key = new SecretKeySpec(SECRET.getBytes(), "HmacSHA512");
        // Parsing the token
        String userName = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        ApplicationUser appUser = userRepository.findByUserName(userName);
        return appUser.getHabits();
    }

    public List<Habit> getAll() {
        return habitRepository.findAll();
    }


}
