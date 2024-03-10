package com.colak.springcachecaffeineutorial.employee.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeineCacheBuilder());
        cacheManager.setCacheNames(someCacheNames()); // Specify cache names if needed
        return cacheManager;
    }

    private Caffeine<Object, Object> caffeineCacheBuilder() {
        Duration duration = Duration.ofMinutes(1);
        return Caffeine.newBuilder()
                .initialCapacity(100) // Initial capacity of the cache
                .maximumSize(500) // Maximum size of the cache
                .expireAfterWrite(duration) // Expiration time in seconds
                .recordStats(); // Enable statistics (optional)
    }

    private Set<String> someCacheNames() {
        Set<String> cacheNames = new HashSet<>();
        cacheNames.add("employees");
        return cacheNames;
    }
}
