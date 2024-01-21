package com.malog.blog.infra.jpa;

import com.malog.blog.presentation.response.BlogInfoRes;


public interface MybatisBlogRepository {
    BlogInfoRes findByBlogId(Long blogId);
}
