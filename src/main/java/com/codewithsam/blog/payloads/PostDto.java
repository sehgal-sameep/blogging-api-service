package com.codewithsam.blog.payloads;

import com.codewithsam.blog.entities.Category;
import com.codewithsam.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;
    private String postTitle;
    private String postContent;

    private String imageName;

    private Date postDate;

    private UserDto user;
    private CategoryDto category;

}
