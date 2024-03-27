package com.codewithsam.blog.payloads;

import com.codewithsam.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private Integer id;
    private String content;
}