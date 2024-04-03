package com.codewithsam.blog.controllers;

import com.codewithsam.blog.config.AppConstants;
import com.codewithsam.blog.entities.Post;
import com.codewithsam.blog.payloads.ApiResponse;
import com.codewithsam.blog.payloads.PostDto;
import com.codewithsam.blog.payloads.PostResponse;
import com.codewithsam.blog.services.FileService;
import com.codewithsam.blog.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/apis")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;


    //create... request body as getting data in json format
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable Integer postId) {
        PostDto post = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(post, HttpStatus.OK);
    }


    //TODO:testing


    //delete
    @DeleteMapping("posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully", true), HttpStatus.OK);
    }


    //getByUser
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser( @PathVariable Integer userId )
    {
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory( @PathVariable Integer categoryId)
    {
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    @GetMapping("posts/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer postId) {
        PostDto post = this.postService.getPost(postId);
        return new ResponseEntity<PostDto>(post, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
                                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY) String sortBy) {
        PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @GetMapping("posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String keywords) {
        List<PostDto> postDtos = this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException {

        PostDto postDto = this.postService.getPost(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/post/image/get/{imageName}")
    public void downloadFile(@PathVariable String imageName, HttpServletResponse response) throws FileNotFoundException {
        //httpServlet response se hm response mai data bhej skte h
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        try {
            StreamUtils.copy(resource, response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
