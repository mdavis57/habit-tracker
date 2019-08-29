package org.launchcode.security;

import org.launchcode.model.ApplicationUser;
import org.launchcode.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationAppUser = userRepository.findByUserName(username);
        if (applicationAppUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationAppUser.getUserName(), applicationAppUser.getPassword(), emptyList());
    }
}