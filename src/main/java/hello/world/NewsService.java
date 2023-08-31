package hello.world;

import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.CacheInvalidate;
import io.micronaut.cache.annotation.CachePut;
import io.micronaut.cache.annotation.Cacheable;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
@CacheConfig("headlines")
public class NewsService {
    private HashMap<String, String> headlines = new HashMap<>() {{
        put("October", "Cached October");
    }};

    @Cacheable
    public String getHeadline(String month) {
        log.info("Not from cache");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            log.error("Interrupted Exception thrown from cache-get", e);
        }
        return headlines.get(month);
    }

    @CachePut(parameters = "month")
    public String addHeadline(String month, String headline) {
        log.info("CachePut month={} headline={}", month, headline);
        headlines.put(month, headline);
        return headline;
    }

    @CacheInvalidate(parameters = "month")
    public void removeHeadline(String month) {
        log.info("CacheInvalidate month={}", month);
        headlines.remove(month);
    }

    @CacheInvalidate(all = true)
    public void removeAll() {
        log.info("CacheInvalidate (all)");
        headlines.clear();
    }

}
