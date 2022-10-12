package com.ftw.hometerview.place.controller.dto;

import lombok.Builder;
import lombok.Getter;

public class StationDto {

    @Getter
    @Builder
    public static class Meta {

        private String stationId;
        private String name;
        private String loadName;
    }

}
