package com.malog.blog.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class BlogInfoRes {
    private String title;
    private String description;
    private Long likeCount;
    private Long viewCount;
    private Long visitCount;
}
