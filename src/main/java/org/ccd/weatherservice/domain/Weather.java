package org.ccd.weatherservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("tm")
    private String date;

    @JsonProperty("iscs")
    private String weatherDetail;

    @JsonProperty("minTa")
    private Double temperatureMin;

    @JsonProperty("maxTa")
    private Double temperatureMax;

    @JsonProperty("avgTa")
    private Double temperatureAvg;

    @JsonProperty("sumRn")
    private Double rainMax;

    @JsonProperty("ddMefs")
    private Double snowMax;

    @JsonProperty("maxWs")
    private Double windMax;

    @JsonProperty("sumGsr")
    private Double sunshineMax;

    @Builder
    public Weather(String date, String weatherDetail, Double temperatureMin, Double temperatureMax, Double temperatureAvg, Double rainMax, Double snowMax, Double windMax, Double sunshineMax) {
        this.date = date;
        this.weatherDetail = weatherDetail;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.temperatureAvg = temperatureAvg;
        this.rainMax = rainMax;
        this.snowMax = snowMax;
        this.windMax = windMax;
        this.sunshineMax = sunshineMax;
    }
}
