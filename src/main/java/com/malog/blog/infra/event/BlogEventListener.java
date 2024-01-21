package com.malog.blog.infra.event;

import com.malog.blog.domain.Blog;
import com.malog.blog.domain.BlogStatistics;
import com.malog.blog.domain.event.BlogInitializedEvent;
import com.malog.blog.domain.repository.BlogRepository;
import com.malog.blog.domain.repository.BlogStatisticsRepository;
import com.malog.member.domain.User;
import com.malog.member.domain.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class BlogEventListener {
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final BlogStatisticsRepository blogStatisticsRepository;

    @EventListener
    public void registerEventListener(BlogInitializedEvent event) {
        User user = userRepository.findByUniqueKey(event.uKey());
        String blogTitle = user.getName() + "님의 블로그";
        String blogDesc = user.getName() + "님의 블로그입니다.";
        Blog savedBlog = blogRepository.save(new Blog(user, blogTitle, blogDesc));
        blogStatisticsRepository.save(new BlogStatistics(savedBlog));
        log.info("uKey : {} 블로그 생성 완료", savedBlog.getUniqueKey());
    }
}
