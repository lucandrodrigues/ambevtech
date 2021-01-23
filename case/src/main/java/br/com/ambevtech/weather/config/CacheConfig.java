package br.com.ambevtech.weather.config;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.event.EventType;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public JCacheCacheManager jCacheCacheManager() {
        JCacheCacheManager jCacheManager = new JCacheCacheManager(cacheManager());
        return jCacheManager;
    }

    @Bean(destroyMethod = "close")
    public CacheManager cacheManager() {

        ResourcePools resourcePools = ResourcePoolsBuilder.newResourcePoolsBuilder().heap(1000, EntryUnit.ENTRIES).offheap(5, MemoryUnit.MB).build();

        CacheConfiguration<Object, Object> cachePrevisao = CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class, resourcePools)
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(CacheNames.cacheDefaultTime)))
                .build();

        CacheConfiguration<Object, Object> cacheListarCidade = CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class, resourcePools)
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(CacheNames.cacheDefaultTime)))
                .build();

        Map<String, CacheConfiguration<?, ?>> caches = new HashMap<>();
		caches.put(CacheNames.cachePrevisao, cachePrevisao);
        caches.put(CacheNames.cacheListarCidade, cacheListarCidade);

        EhcacheCachingProvider provider = (EhcacheCachingProvider) Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider");
        org.ehcache.config.Configuration configuration = new DefaultConfiguration(caches, provider.getDefaultClassLoader());
        return provider.getCacheManager(provider.getDefaultURI(), (org.ehcache.config.Configuration) configuration);
    }
}
