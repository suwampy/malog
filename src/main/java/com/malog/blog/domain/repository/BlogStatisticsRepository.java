package com.malog.blog.domain.repository;

import com.malog.blog.domain.BlogStatistics;

public interface BlogStatisticsRepository {
    BlogStatistics save(BlogStatistics blogStatistics);
}
