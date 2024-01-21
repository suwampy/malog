package com.malog.blog.presentation;

import com.malog.blog.domain.repository.BlogReader;
import com.malog.blog.presentation.response.BlogInfoRes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class BlogQueryApi {
    private final BlogReader blogReader;

    @GetMapping(value = "/api/v1/blog/{blogId}")
    public ResponseEntity<BlogInfoRes> findBlogInfo(@PathVariable Long blogId) {
        return ResponseEntity.ok(blogReader.findByBlogId(blogId));
    }

}
