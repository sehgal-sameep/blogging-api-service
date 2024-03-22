package com.codewithsam.blog.payloads;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
//@Data
public class JwtAuthResponse {
    private String jwtToken;
    private String username;
}
