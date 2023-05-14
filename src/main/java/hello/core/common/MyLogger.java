package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("uuid = " + uuid);
        System.out.println("requestURL = " + requestURL);
        System.out.println("message = " + message);
    }

    @PostConstruct
    public void UUID() {
        uuid = UUID.randomUUID().toString();
        System.out.println("init uuid = " + uuid+" "+this);
    }

    @PreDestroy
    public void close() {
        System.out.println("close uuid = " + uuid+" "+this);
    }

}
