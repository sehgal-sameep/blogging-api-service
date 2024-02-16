package com.codewithsam.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithsam.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
