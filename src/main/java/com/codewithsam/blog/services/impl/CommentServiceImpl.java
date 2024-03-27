package com.codewithsam.blog.services.impl;

import com.codewithsam.blog.entities.Comment;
import com.codewithsam.blog.entities.Post;
import com.codewithsam.blog.entities.User;
import com.codewithsam.blog.exceptions.ResourceNotFoundException;
import com.codewithsam.blog.payloads.CommentDto;
import com.codewithsam.blog.payloads.PostDto;
import com.codewithsam.blog.repositories.CommentRepo;
import com.codewithsam.blog.repositories.PostRepo;
import com.codewithsam.blog.repositories.UserRepo;
import com.codewithsam.blog.services.CommentService;
import com.codewithsam.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
//        User user = this.userRepo.findById(userId).
//                orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
//        comment.setUser(user);
        Comment createdComment = this.commentRepo.save(comment);
        return this.modelMapper.map(createdComment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        return null;
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
        this.commentRepo.delete(comment);
    }
}
