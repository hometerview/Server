package com.ftw.hometerview.place.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Log4j2
@Component
public class NaverMapGeocodeAPI {

    @Builder
    @Getter
    public static class GeoPoint {

        Double lat;
        Double lon;
    }

    private static final String BASE_URL = "https://naveropenapi.apigw.ntruss.com";
    private static final String PATH = "/map-geocode/v2/geocode";
    @Value("${naver.api-key-id}")
    private String API_KEY_ID;
    @Value("${naver.api-key}")
    private String API_KEY;

    public GeoPoint convert(String address) {
        WebClient client = WebClient.builder()
            .baseUrl(BASE_URL)
            .build();

        ObjectMapper objectMapper = new ObjectMapper();

        String responseJson = client.get()
            .uri(uriBuilder -> uriBuilder
                .path(PATH)
                .queryParam("query", address)
                .build())
            .header("X-NCP-APIGW-API-KEY-ID", API_KEY_ID)
            .header("X-NCP-APIGW-API-KEY", API_KEY)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        Map<String, Object> responseMap = null;
        try {
            responseMap = objectMapper.readValue(responseJson, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List<Map<String, String>> addressesMap = (List<Map<String, String>>) responseMap.get(
            "addresses");
        Map<String, String> addressMap = addressesMap.get(0);
        String lon = addressMap.get("x");
        String lat = addressMap.get("y");
        return GeoPoint.builder()
            .lat(Double.parseDouble(lat))
            .lon(Double.parseDouble(lon))
            .build();
    }
}
