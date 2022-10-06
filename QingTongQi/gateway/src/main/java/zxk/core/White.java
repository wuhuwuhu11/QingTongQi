package zxk.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "white.ignore")
public class White {
    private List<String> whiteUrls;

    public List<String> getWhiteUrls() {
        return whiteUrls;
    }

    public void setWhiteUrls(List<String> whiteUrls) {
        this.whiteUrls = whiteUrls;
    }
}
