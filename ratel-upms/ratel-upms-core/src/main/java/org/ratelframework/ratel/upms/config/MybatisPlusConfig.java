package org.ratelframework.ratel.upms.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <P>
 *  Something configuration of Mybatis plus
 * </P>
 * @author whd.java@gmail.com
 * @date 2019/11/1 15:53
 * @since 1.0.0
 */
@Configuration
@MapperScan("org.ratelframework.ratel.upms.mapper")
public class MybatisPlusConfig {


    /**
     * Register pagination interceptor bean to Spring context
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
