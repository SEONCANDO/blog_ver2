package com.sunny.blog.web.dto;

import com.sunny.blog.domain.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime modifiedTime;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.writer = entity.getWriter();
        this.content = entity.getContent();
        this.modifiedTime = entity.getModifiedDate();
    }
}
