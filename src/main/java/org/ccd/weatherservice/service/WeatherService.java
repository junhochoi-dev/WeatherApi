package org.ccd.weatherservice.service;

import lombok.RequiredArgsConstructor;
import org.ccd.weatherservice.data.WeatherRequest;
import org.ccd.weatherservice.domain.Weather;
import org.ccd.weatherservice.repository.WeatherRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class WeatherService {
    private final WebClient webClient;
    private final WeatherRepository weatherRepository;

    static String APIURL = "https://apis.data.go.kr/1360000/AsosDalyInfoService/getWthrDataList?";
    static String APIKEY = "VrTrxmDgK7SwSmqta6hSDBRkMCehSfTOUoRp71BD7dpNzJxI72W12CXmMh3Pi1%2B8NtFbKQE1aihAezArmiC2bA%3D%3D";

    static String BASEURL = APIURL
            + "serviceKey=" + APIKEY
            + "&pageNo=1&numOfRows=400"
            + "&dataType=JSON&dataCd=ASOS&dateCd=DAY"
            + "&stnIds=108";

    public void initialize() {
        List<WeatherRequest> weatherRequestList = Flux.range(2019, 2023)
                .flatMap(year -> webClient.get()
                        .uri(BASEURL + "&startDt=" + year + "0101" + "&endDt=" + year + "1231")
                        .retrieve()
                        .bodyToMono(WeatherRequest.class)
                )
                .collectList()
                .block();

        List<Weather> weatherList = new ArrayList<>();
        weatherRequestList.forEach(weatherRequest ->
                weatherList.addAll(weatherRequest
                        .getResponse()
                        .getBody()
                        .getWeatherList())
        );
        weatherRepository.saveAll(weatherList);
    }
}
