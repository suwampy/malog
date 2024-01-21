package com.malog.blog.infra.jpa;

import com.malog.blog.domain.BlogStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBlogStatisticsRepository extends JpaRepository<BlogStatistics, Long> {

}
