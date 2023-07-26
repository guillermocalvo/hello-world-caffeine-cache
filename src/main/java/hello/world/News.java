package hello.world;

import io.micronaut.serde.annotation.Serdeable;

import java.time.Month;
import java.util.List;

@Serdeable

public class News {
    public News(String month, List<String> headlines) {
        this.headlines = headlines;
        this.month=month;
    }

    String month;
    List<String> headlines;
}
