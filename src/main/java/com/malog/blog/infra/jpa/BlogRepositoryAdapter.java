package com.malog.blog.infra.jpa;

import com.malog.blog.domain.Blog;
import com.malog.blog.domain.repository.BlogRepository;
import com.malog.blog.presentation.response.BlogInfoRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BlogRepositoryAdapter implements BlogRepository {
    private final JpaBlogRepository jpaBlogRepository;

    @Override
    public Blog save(Blog blog) {
        return jpaBlogRepository.save(blog);
    }
}
