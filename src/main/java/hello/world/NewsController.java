package hello.world;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
public class NewsController {

@Inject
NewsService newsService;

    @Get("/{month}")
    public String index(String month){
        log.info("hit service");
        new News(month, Collections.singletonList(newsService.headlines(month)));
        return newsService.headlines.get(month);

    }
}
