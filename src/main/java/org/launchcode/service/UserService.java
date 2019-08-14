package org.launchcode.service;

import org.launchcode.model.User;
import org.launchcode.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUser(Long id) { return userRepository.findOne(id); }

    public User addUser(User user) {
        return userRepository.save(user);
    }


}
