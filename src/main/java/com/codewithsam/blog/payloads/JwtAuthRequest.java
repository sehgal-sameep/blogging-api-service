package com.codewithsam.blog.payloads;

import lombok.*;

@Data
public class JwtAuthRequest {
    private String email;
    private String password;
}
