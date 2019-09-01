
package org.launchcode.service;


import lombok.extern.slf4j.Slf4j;
import org.launchcode.model.ApplicationUser;
import org.launchcode.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ApplicationUser addUser(ApplicationUser applicationUser) {
        applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        return userRepository.save(applicationUser);
    }


    public Map<String, Long> getUserId(HttpServletRequest request)
    {
        String token = request.getHeader(HEADER_STRING);

        if (token == null)
        {
            throw new RuntimeException("token is null");
        }
        SecretKey key = new SecretKeySpec(SECRET.getBytes(), "HmacSHA512");
        // parse the token.
        String userName = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        ApplicationUser appUser = userRepository.findByUserName(userName);

        Map<String, Long> response = new HashMap<>();

        response.put("userId", appUser.getId());
        return response;
    }
}