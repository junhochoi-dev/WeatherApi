package org.ccd.weatherservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ccd.weatherservice.data.WeatherRequest;
import org.ccd.weatherservice.domain.Weather;
import org.ccd.weatherservice.repository.WeatherRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class WeatherService {
    private final WebClient webClient;
    private final WeatherRepository weatherRepository;

    static String APIURL = "/1360000/AsosDalyInfoService/getWthrDataList?";
    static String APIKEY = "VrTrxmDgK7SwSmqta6hSDBRkMCehSfTOUoRp71BD7dpNzJxI72W12CXmMh3Pi1%2B8NtFbKQE1aihAezArmiC2bA%3D%3D";

    static String BASEURL = APIURL
            + "serviceKey=" + APIKEY
            + "&pageNo=1&numOfRows=400&dataType=JSON&dataCd=ASOS&dateCd=DAY";
    public void initialize() {
        log.info("########## INIT START");

        List<WeatherRequest> weatherRequestList = Flux.range(2019, 5)
                .flatMap(year -> webClient.get()
                        .uri(uriBuilder -> uriBuilder.path(BASEURL + "&startDt=" + year + "0101" + "&endDt=" + year + "1231" + "&stnIds=108").build())
                        .retrieve()
                        .bodyToMono(WeatherRequest.class)
                        .timeout(Duration.ofSeconds(100))
                )
                .collectList()
                .block();

        List<Weather> weatherList = new ArrayList<>();
        weatherRequestList.forEach(weatherRequest ->
                weatherList.addAll(weatherRequest
                        .getResponse()
                        .getBody()
                        .getWeatherList().getWeathers())
        );
        weatherRepository.saveAll(weatherList);
        log.info("########## INIT END");
    }
}
