package com.malog.blog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "blog_statistics")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class BlogStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @Column(nullable = false)
    private Long viewCount;

    @Column(nullable = false)
    private Long likeCount;

    @Column(nullable = false)
    private Long visitCount;

    public BlogStatistics(Blog blog) {
        this.blog = blog;
        viewCount = 0L;
        likeCount = 0L;
        visitCount = 0L;
    }
}
