package com.malog.common.config;

import com.malog.blog.domain.repository.BlogReader;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@Import(BlogReader.class)
public class QueryConfiguration {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        var factoryBean = new SqlSessionFactoryBean();
        var configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfiguration(configuration);
        var resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver
            .getResources("classpath:mapper/*.xml"));
        return factoryBean.getObject();
    }
}
