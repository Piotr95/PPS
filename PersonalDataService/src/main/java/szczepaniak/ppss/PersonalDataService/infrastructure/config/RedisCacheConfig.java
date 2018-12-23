package szczepaniak.ppss.PersonalDataService.infrastructure.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
@Configuration
public class RedisCacheConfig {
    @Primary
    @Bean(name = "cacheManager")
    CacheManager redisCache(RedisConnectionFactory redisConnectionFactory)
    {
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .build();
    }
}

