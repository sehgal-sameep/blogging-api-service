package com.codewithsam.blog.services;

import com.codewithsam.blog.payloads.CommentDto;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface CommentService {


    //create
    public CommentDto createComment(CommentDto commentDto, Integer postId);

    //update
    public CommentDto updateComment(CommentDto commentDto, Integer commentId);

    //delete
    public void deleteComment(Integer commentId);
}
