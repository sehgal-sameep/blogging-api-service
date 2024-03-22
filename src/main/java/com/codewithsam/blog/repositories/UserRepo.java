package com.codewithsam.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithsam.blog.entities.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    //it will be used in customUserDetailService
    Optional<User> findByEmail(String email);
}
