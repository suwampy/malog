package com.malog.blog.domain.repository;


import com.malog.blog.infra.jpa.MybatisBlogRepository;
import com.malog.blog.presentation.response.BlogInfoRes;
import lombok.AllArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@AllArgsConstructor
public class BlogReader implements MybatisBlogRepository {
    private final SqlSessionTemplate sqlSessionTemplate;

    @Override
    public BlogInfoRes findByBlogId(Long blogId) {
        return sqlSessionTemplate.getMapper(MybatisBlogRepository.class).findByBlogId(blogId);
    }
}
