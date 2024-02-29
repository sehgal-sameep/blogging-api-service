package com.codewithsam.blog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import java.util.Date;

@Entity
@Table( name="posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column
    private String postTitle;

    @Column
    private String postContent;

    @Column
    private String imageName;

    @Column
    private Date postDate;

    @ManyToOne
    private User user;

    @ManyToOne
//    @JoinColumn(name= "category_id")
    private Category category;
}
