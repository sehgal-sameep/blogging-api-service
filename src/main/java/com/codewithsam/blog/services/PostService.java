package com.codewithsam.blog.services;

import com.codewithsam.blog.entities.Category;
import com.codewithsam.blog.entities.Post;
import com.codewithsam.blog.entities.User;
import com.codewithsam.blog.payloads.PostDto;
import com.codewithsam.blog.payloads.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //getAllPosts
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy);

    //getSinglePost
    PostDto getPost(Integer postId);

    //getPostByCategory
    List<PostDto> getPostsByCategory(Integer categoryId);

    //getPostByUser
    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);

}
