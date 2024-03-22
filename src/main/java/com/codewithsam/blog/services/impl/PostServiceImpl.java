package com.codewithsam.blog.services.impl;

import com.codewithsam.blog.entities.Category;
import com.codewithsam.blog.entities.Post;
import com.codewithsam.blog.entities.User;
import com.codewithsam.blog.exceptions.ResourceNotFoundException;
import com.codewithsam.blog.payloads.CategoryDto;
import com.codewithsam.blog.payloads.PostDto;
import com.codewithsam.blog.payloads.PostResponse;
import com.codewithsam.blog.repositories.CategoryRepo;
import com.codewithsam.blog.repositories.PostRepo;
import com.codewithsam.blog.repositories.UserRepo;
import com.codewithsam.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).
                orElseThrow(()->new ResourceNotFoundException("User","User ID", userId));
        Category category = this.categoryRepo.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("Category","Category ID", categoryId));
        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setPostDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = this.postRepo.save(post);
        return this.modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).
                orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        post.setPostContent(postDto.getPostContent());
        post.setPostTitle(postDto.getPostTitle());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).
                orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Post> pagedPost = this.postRepo.findAll(p);
        List<Post> posts = pagedPost.getContent();

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagedPost.getNumber());
        postResponse.setPageSize(pagedPost.getSize());
        postResponse.setTotalElements(pagedPost.getNumberOfElements());
        postResponse.setTotalPages(pagedPost.getTotalPages());
        postResponse.setLastPage(pagedPost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPost(Integer postId) {
        Post post = this.postRepo.findById(postId).
                orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        return this.modelMapper.map(post,PostDto.class);

    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
        List <Post> posts = this.postRepo.findByCategory(cat);
        List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findBypostTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
