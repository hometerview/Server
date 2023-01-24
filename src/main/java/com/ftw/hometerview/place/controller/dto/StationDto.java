package com.ftw.hometerview.place.controller.dto;

import lombok.Builder;
import lombok.Getter;

public class StationDto {

    @Getter
    @Builder
    public static class Meta {

        private String stationId;
        private String lineNumber;
        private String name;
        private String lotNumberAddress;
        private String roadAddress;
        private Double lat;
        private Double lon;
    }

}
