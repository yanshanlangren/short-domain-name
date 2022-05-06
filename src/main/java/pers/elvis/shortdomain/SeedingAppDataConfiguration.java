package pers.elvis.shortdomain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SeedingAppDataConfiguration {

    @Bean
    public Map<String, String> perDomainMap() {
        return new HashMap<>();
    }

    @Bean
    public long perDomainSeq() {
        return 3521614606208L;
    }
}
