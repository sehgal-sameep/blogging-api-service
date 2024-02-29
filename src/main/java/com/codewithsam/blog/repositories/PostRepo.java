package com.codewithsam.blog.repositories;

import com.codewithsam.blog.entities.Category;
import com.codewithsam.blog.entities.Post;
import com.codewithsam.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
