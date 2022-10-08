package dev.gustavoteixeira.easygest.adapter;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class JwtProperties {

    private String secretKey = "eyJhbGciOiJIUzI1NiJ9";

    // validity in milliseconds
    private long validityInMs = 3600000 * 24 * 7; // 1h

}
