package br.com.santospage.taxassistant.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${fast.host}")
    private String fastHost;

    @Value("${fast.port}")
    private String fastPort;

    @Bean
    public WebClient webClient() {
        String baseUrl = String.format("http://%s:%s", fastHost, fastPort);
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
