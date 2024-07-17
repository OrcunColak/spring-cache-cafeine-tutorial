package com.colak.springcachecaffeineutorial.employee.config;

import com.colak.springcachecaffeineutorial.employee.jpa.Employee;
import com.colak.springcachecaffeineutorial.employee.repository.EmployeeRepository;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// This class is just an example to use LoadingCache as a bean.
// This bean can not be used with org.springframework.cache.CacheManager class
@Configuration
public class LoadingCacheConfig {

    // Configure the LoadingCache
    @Bean
    public LoadingCache<Long, Employee> loadingCache(EmployeeRepository repository) {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(100)
                .build(new CacheLoader<>() {
                    // Load data for a single key
                    @Override
                    public Employee load(Long key) {
                        return fetchDataFromDatabase(key, repository);
                    }

                    // Load data for multiple keys
                    @Override
                    public Map<Long, Employee> loadAll(Set<? extends Long> keys) {
                        Map<Long, Employee> result = new HashMap<>();
                        for (Long key : keys) {
                            result.put(key, fetchDataFromDatabase(key, repository));
                        }
                        return result;
                    }
                });
    }

    // Simulate fetching data from a database
    private Employee fetchDataFromDatabase(Long key, EmployeeRepository repository) {
        return repository.findById(key)
                .orElse(null);
    }
}
