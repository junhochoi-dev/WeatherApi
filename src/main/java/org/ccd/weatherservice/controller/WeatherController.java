package org.ccd.weatherservice.controller;

import lombok.RequiredArgsConstructor;
import org.ccd.weatherservice.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/initialize")
    public void initialize() {
        weatherService.initialize();
    }
}
