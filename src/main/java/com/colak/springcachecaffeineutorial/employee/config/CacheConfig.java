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

    // Another way of configuration is
    // spring.cache.type=caffeine
    // spring.cache.cache-names=employees,someothercache
    // spring.cache.caffeine.spec=initialCapacity=50,maximumSize=500,expireAfterAccess=60s,expireAfterWrite=10m
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeineCacheBuilder());
        cacheManager.setCacheNames(someCacheNames()); // Specify cache names if needed
        return cacheManager;
    }

    // Custom configuration for different caches
    // @Bean
    // public CacheManager cacheManager() {
    //     List<CaffeineCache> caches = new ArrayList<>();
    //
    //     caches.add(buildCache("PokeCache", Duration.of(10, ChronoUnit.SECONDS)));
    //     // add more caches here ....
    //
    //     SimpleCacheManager cacheManager = new SimpleCacheManager();
    //     cacheManager.setCaches(caches);
    //
    //     return cacheManager;
    // }

    // Expire After Access: Expires the entry after the specified duration is passed since the last read or write occurs.
    // Expire After Write: Expires the entry after the specified duration is passed since the last write occurs.
    // Expire After: Custom expiry for each entry based on custom expiry implementation.
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
