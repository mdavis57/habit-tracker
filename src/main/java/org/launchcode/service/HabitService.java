package org.launchcode.service;


import lombok.extern.slf4j.Slf4j;
import org.launchcode.repository.HabitRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HabitService {
    private HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;

    }



}
