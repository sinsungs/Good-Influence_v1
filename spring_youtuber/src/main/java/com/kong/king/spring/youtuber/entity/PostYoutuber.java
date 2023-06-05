package com.kong.king.spring.youtuber.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PostYoutuber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pyno;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "youtuber_id")
    private Youtuber youtuber;

    public void setPost(Post post) {
        this.post = post;
    }
    // Define getters and setters

    // Define other necessary methods
}