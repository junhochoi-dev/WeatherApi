package org.ccd.weatherservice.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.ccd.weatherservice.domain.Weather;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class WeatherRequest {
    @JsonProperty("response")
    private Response response;

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    @Setter
    public static class Response {
        @JsonProperty("header")         private     Header  header;
        @JsonProperty("body")           private     Body    body;

        @AllArgsConstructor(access = AccessLevel.PROTECTED)
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        @Getter
        @Setter
        public static class Header {
            @JsonProperty("resultCode") private     String      resultCode;
            @JsonProperty("resultMsg")  private     String      resultMsg;
        }

        @AllArgsConstructor(access = AccessLevel.PROTECTED)
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        @Getter
        @Setter
        public static class Body {
            @JsonProperty("dataType")   private     String          dataType;
            @JsonProperty("items")      private     WeatherList   weatherList;
            @JsonProperty("pageNo")     private     int             pageNo;
            @JsonProperty("numOfRows")  private     int             numOfRows;
            @JsonProperty("totalCount") private     int             totalCount;

            @AllArgsConstructor(access = AccessLevel.PROTECTED)
            @NoArgsConstructor(access = AccessLevel.PROTECTED)
            @Getter
            @Setter
            public static class WeatherList {
                @JsonProperty("item")
                List<Weather> weathers;
            }
        }
    }
}
