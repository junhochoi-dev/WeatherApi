package org.ccd.weatherservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class WebClientConfig {
    private final static String BASEURL = "https://apis.data.go.kr";
    @Bean
    public WebClient webClient() {
        // Encoding
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASEURL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        // Buffer Size
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1)).build();

        return WebClient.builder()
        .uriBuilderFactory(factory)
        .exchangeStrategies(exchangeStrategies)
        .baseUrl(BASEURL)
        .build();
    }
}
