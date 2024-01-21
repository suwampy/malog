package com.malog.blog.infra.jpa;

import com.malog.blog.domain.BlogStatistics;
import com.malog.blog.domain.repository.BlogStatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component

@AllArgsConstructor
public class BlogStatisticsRepositoryAdapter implements BlogStatisticsRepository {
    private final JpaBlogStatisticsRepository jpaBlogStatisticsRepository;

    @Override
    public BlogStatistics save(BlogStatistics blogStatistics) {
        return jpaBlogStatisticsRepository.save(blogStatistics);
    }
}
