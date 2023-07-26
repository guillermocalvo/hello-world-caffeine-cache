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
    HashMap<String, String> headlines = new HashMap<String, String>(){{put("October", "Cached October");
    }};

    @Cacheable
    public String headlines(String month){
         try{
            TimeUnit.SECONDS.sleep(5);
            log.info("Not from cache");
            return headlines.get(month);
            }catch(InterruptedException e){
            System.out.println("Interrupted Exception thrown from index");
            }
        return"nope";

    }

    @CachePut(parameters="month")
    public HashMap<String, String> addHeadline(String month, String headline){
        String l = headlines.getOrDefault(month, "");
        headlines.put(l, headline);
        return headlines;
    }

    @CacheInvalidate(parameters="month")
    public HashMap<String, String> removeHeadline(String month, String headline){
        String l = headlines.getOrDefault(month, "");
        headlines.remove(l, headline);
        return headlines;
    }

}
