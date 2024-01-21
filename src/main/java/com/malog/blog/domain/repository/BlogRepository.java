package com.malog.blog.domain.repository;

import com.malog.blog.domain.Blog;
import com.malog.blog.presentation.response.BlogInfoRes;

public interface BlogRepository {
    Blog save(Blog blog);
}
