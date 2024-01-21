package com.malog.blog.infra.jpa;

import com.malog.blog.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBlogRepository extends JpaRepository<Blog, Long> {

}
