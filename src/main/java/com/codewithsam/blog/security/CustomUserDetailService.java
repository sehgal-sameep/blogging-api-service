package com.codewithsam.blog.security;

import com.codewithsam.blog.entities.User;
import com.codewithsam.blog.exceptions.ResourceNotFoundException;
import com.codewithsam.blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database
        User user = this.userRepo.findByEmail(username).
                orElseThrow(() -> new ResourceNotFoundException("User", "Email : " + username, 0));
        return user;
    }
}
