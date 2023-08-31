package hello.world;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NewsController {

    @Inject
    NewsService newsService;

    @Get("/cache-get/{month}")
    public String cacheGet(String month) {
        log.info("hit cache-get");
        String headline = newsService.getHeadline(month);
        return "cache-get: month=" + month + " headline=" + headline;
    }

    @Get("/cache-put/{month}/{headline}")
    public String cachePut(String month, String headline) {
        log.info("hit cache-put");
        String cached = newsService.addHeadline(month, headline);
        return "cache-put: month=" + month + " headline=" + cached;
    }

    @Get("/cache-invalidate/{month}")
    public String cacheInvalidate(String month) {
        log.info("hit cache-invalidate");
        newsService.removeHeadline(month);
        return "cache-invalidate: month=" + month;
    }

    @Get("/cache-invalidate/ALL")
    public String cacheInvalidateAll() {
        log.info("hit cache-invalidate ALL");
        newsService.removeAll();
        return "cache-invalidate: ALL";
    }
}
