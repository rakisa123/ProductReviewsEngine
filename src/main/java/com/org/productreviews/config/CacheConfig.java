package com.org.productreviews.config;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import javax.cache.CacheManager;

import static org.ehcache.config.builders.CacheConfigurationBuilder.newCacheConfigurationBuilder;
import static org.ehcache.config.builders.ResourcePoolsBuilder.newResourcePoolsBuilder;
import static org.ehcache.jsr107.Eh107Configuration.fromEhcacheCacheConfiguration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig  implements JCacheManagerCustomizer {

    public static final String ALL_PRODUCTS_CACHE_KEY = "allproductscache";
    
    @Value("${org.productreviews.service.cache.allproducts.expiry}")
    private int productsCacheExpiry;
	
    @Override
    public void customize(CacheManager cacheManager) {
        cacheManager.createCache(ALL_PRODUCTS_CACHE_KEY, fromEhcacheCacheConfiguration(
                getDefaultCacheConfigConfiguration(1000, productsCacheExpiry)));  
    }

    private CacheConfiguration<Object, Object> getDefaultCacheConfigConfiguration(int entries, int expireTimeInSeconds) {
        return newCacheConfigurationBuilder(Object.class, Object.class,
                newResourcePoolsBuilder().heap(entries, EntryUnit.ENTRIES).build())
        		    .withExpiry(Expirations.timeToLiveExpiration(Duration.of(expireTimeInSeconds, TimeUnit.SECONDS)))
                    .build();
    }
}
