# Read Me First

Original idea is from  
https://medium.com/@bazzi.mouad/unlocking-peak-performance-in-your-spring-boot-applications-6b4b2199277f

Another example is at  
https://medium.com/@mikael_55667/effortless-caching-with-caffeine-in-spring-boot-a-must-use-for-api-caching-32b54b68e7e9

Another example is at
https://medium.com/@ak123aryan/supercharge-your-spring-boot-application-with-caffeine-cache-aeb17c4002bd

# Swagger

Go to  
http://localhost:8080/swagger-ui/index.html

# Configuration

````properties
spring.cache.type=caffeine
spring.cache.cache-names=buckets
spring.cache.caffeine.spec=initialCapacity=50,maximumSize=500,expireAfterAccess=60s,expireAfterWrite=10m
```