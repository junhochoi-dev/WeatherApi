package org.ccd.weatherservice.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ccd.weatherservice.domain.Weather;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WeatherRequest {
    private Response response;

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class Response {
        @JsonProperty("header")
        private Header header;
        @JsonProperty("body")
        private Body body;

        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        @Getter
        public static class Header {
            @JsonProperty("resultCode")
            String resultCode;
            @JsonProperty("resultMsg")
            String resultMsg;
        }


        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        @Getter
        public static class Body {
            private @JsonProperty("dataType") String dataType;
            private @JsonProperty("items") List<Weather> weatherList;
            private @JsonProperty("pageNo") int pageNo;
            private @JsonProperty("numOfRows") int numOfRows;
            private @JsonProperty("totalCount") int totalCount;
        }
    }
}
