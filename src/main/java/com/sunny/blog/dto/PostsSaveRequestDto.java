package com.sunny.blog.dto;

import com.sunny.blog.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostsSaveRequestDto {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime modifiedTime;

    @Builder
    public PostsSaveRequestDto(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
