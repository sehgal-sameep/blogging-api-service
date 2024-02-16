package com.codewithsam.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithsam.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
